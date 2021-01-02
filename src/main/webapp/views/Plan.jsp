<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">

    <title>eCare plans</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>
<jsp:include page="/views/Navbar.jsp"/>

<div class="container">

    <br/>
    <h2>Plan for your mobile phone</h2>
    <br/>

    <div class="b-tarif-cards_center_block">

        <div class="b-tarif-cards_center_block_left">
            <div class="b-tarif-cards_center_block_left_main a">
                <div class="b-tarif-cards_center_infoblock_icon">
                    <img src="https://spb.shop.megafon.ru/files-pk-ceph/svg/ac3834e1-31c5-4ccf-8258-bcfb3eb64577/icon-full_set_24px-pigbank_24.svg"
                         style="width:32px; height: 32px;">
                </div>
                <span class="b-tarif-cards_center_block_left_main_text">
                    <a href="https://www.megafon.ru/go/kopilka">Копилка</a>
                    <br>
                    <div class="b-tarif-cards_center_block_left_caption">Подключите, и гигабайты и минуты всегда будут с вами!</div>
                    <span class="b-tarif-cards_center_block_left_footnote_text footnote">Подробнее</span>
                    <div class="b-tarif-cards_center_block_left_footnote footnote_hide">Все неизрасходованные остатки интернета и минут накапливаются и начнут расходоваться автоматически, когда закончится основной пакет по тарифу. Подключите бесплатную опцию в <a
                    target="_blank" href="http://mlk.mgfn.ru">приложении</a> «МегаФон» или в <a
                    target="_blank" href="https://lk.megafon.ru/">Личном кабинете</a>.</div>
                </span>
            </div>
        </div>
        <!-- slider -->

        <div class="b-tarif-cards_center_block_right">
            <span class="b-tarif-cards__payment">
                до 100 ГБ и до 1200 минут
            </span>
            <span class="b-tarif-cards__unit"></span>
        </div>
    </div>

</div>
<!-- Footer -->
${footer}

</body>
</html>