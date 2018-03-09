<html>
<head>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-1/css/bootstrap.min.css"/>
    <link type="text/css" href="/css/checkout.css" rel="stylesheet" />
    <script type="text/javascript"  src="/js/checkout.js"></script>
</head>
<body>

<div class="container">
    <#if errors??>
    <div class="alert alert-danger">
        There were problems with the data you entered:
        <ul>
            <#list errors as error>
            <li>${error}</li>
            </#list>
        </ul>
    </div>
    </#if>
<form class="checkout-form" action="/payments" method="post">
    <fieldset>
        <legend>Buyer</legend>
        <div class="form-group row">
            <div class="col-sm-12">
                <label for="inputName">Name</label>
                <input type="text" name="buyer.name" class="form-control" id="inputName" placeholder="Name">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-12">
                <label for="inputEmail">Email</label>
                <input type="email" name="buyer.email" class="form-control" id="inputEmail" placeholder="Enter email">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-3">
                <label for="inputCpf">CPF</label>
                <input type="text" name="buyer.cpf" class="form-control" id="inputCpf">
            </div>
        </div>
    </fieldset>
    <fieldset>
    <legend>Payment</legend>
    <div class="form-group row">
        <div class="col-sm-3">
            <label for="inputAmount">Amount</label>
            <input type="text" name="payment.amount" class="form-control" id="inputAmount">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-3">
            <label for="selectType">Payment method</label>
            <select class="form-control" onchange="showHideCard()" name="payment.type" id="selectType">
                <#list types as type>
                <option>${type}</option>
                </#list>
            </select>
        </div>
    </div>
    </fieldset>
    <fieldset id="card-fieldset">
        <legend>Card</legend>
        <div class="form-group row">
            <div class="col-sm-12">
                <label for="inputHolderName">Holder name</label>
                <input type="text" name="payment.card.holderName" class="form-control" id="inputHolderName"
                       placeholder="Holder name">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-12">
                <label for="inputNumber">Number</label>
                <input type="text" name="payment.card.number" class="form-control" id="inputNumber"
                       placeholder="Number">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-12">
                <label for="inputCvv">CVV</label>
                <input type="text" name="payment.card.cvv" class="form-control" id="inputCvv" placeholder="Number">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-3">
                <label for="inputExpDate">Expiration date</label>
                <input type="text" name="payment.card.expirationDate" class="form-control" id="inputExpDate">
                <input type="text" class="invisible" name="client.id" value=${client.id}>
            </div>
        </div>
    </fieldset>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
</body>
</html>

