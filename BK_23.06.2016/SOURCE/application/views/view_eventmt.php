<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<?php
@require_once APPPATH . '/libraries/LikeShare.php';
$LikeShare = new LikeShare;
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=no"/>
        <meta name="apple-mobile-web-app-capable" content="yes"/>
        <meta name="apple-touch-fullscreen" content="yes"/> 
        <link rel="stylesheet" href="/assets/css/reset.css">
        <script type="text/javascript" src="/admin/assets/js/plugins/jquery-1.8.3.min.js"></script>
        <title></title>
        <style>
            body{
                background: #9C9C9C;
                font-family: Tahoma;
            }
            a{
                text-decoration: none;
            }
            .content{
                max-width: 600px;
                background-color: #fff;
                margin: auto;
            }
            .info-number{
                max-width: 600px;
                padding-bottom: 15px;
                margin-top: -2px;
                padding: 15px;
            }
            .info-number img{
                float: left;
                margin-bottom: 18px;
                width: 57px;
                margin-right: 10px;
                position: absolute;
            }
            .info-number span#title{
                display: block;
                font-weight: bold;
                color: #CE171F;
                font-size: 14px;
                /*                overflow: hidden;
                                white-space: nowrap;
                                text-overflow: ellipsis;
                                line-height: 16px;*/
                width: 100%;
                margin-top: 8px;
            }
            .info-number span#day{
                display: block;
                padding: 10px 0 5px;
                color: #333333;
                font-size: 12px;
                font-weight: bold;
            }
            .info-number button{
                float: right;
                margin-top: -8%;
                margin-right: 3%;
                color: #fff;
                background-color: #CE171F;
                border: none;
                border-bottom: 4px solid #9C2729;
                padding: 11px 20px 11px 20px;
                border-radius: 4px;
                width: 103px;
                cursor: pointer;
            }
            hr{
                margin-top: 20px;
                margin-bottom: -5px;
            }

            .input-phone-login{
                text-align: center;
                max-width: 390px;
                background-color: #fff;
                padding: 1% 2% 1% 2%;
                border-radius: 10px;
                border: 1px solid #9E9E9E;
                margin: auto;
                padding-bottom: 17px;
                margin-top: 3%;
                position: relative;
                z-index: 1;
            }
            .input-phone-login span{
                font-family: "UTM-Facebook";
                color: #8a140c;
                font-size: 20px;
                margin-top: 1%;
                display: block;
                margin-bottom: 10px;
            }
            .input-phone-login input[name=phone]{
                border-radius: 5px;
                border: 1px solid #738191;
                padding: 10px;
                text-align: center;
                color: #738191;
                height: 20px;
                width: 250px;
                margin-top: 5px;
            }
            .input-phone-login input[name=submit]{
                background-color: #560708;
                border: none;
                width: 145px;
                height: 40px;
                border-radius: 5px;
                margin-top: 15px;
                color: #fff;
                font-size: 20px;
                font-weight: bold;
                cursor: pointer;

            }
            .input-phone-login input[name=submit]:hover{
                opacity: 0.9;
            }

            .shadow{
                background: #333333;
                position: fixed;
                width: 100%;
                height: 100%;
                z-index: 0;
            }
            .space{
                width: 100%;
                height: 70px;
            }
            .end{
                width: 100%;
                text-align: center;
                line-height: 200px;
                color: red;
                font-weight: bold;
                font-size: 20px;
            }
            .info-number-content{
                /*width: 52%;*/
                margin-left: 65px;
            }
            .info-number-button{
                float: right;
                width: 21%;
                margin-top: -36px;
            }
            .info-number-title{
                width: 60%;
            }
            .gift-code{
                margin-left: 10px;
                margin-top: 10px;
                font-size: 16px;
                color: #CE171F;
                font-weight: bold;
            }
            .copy{
                cursor: pointer;
                background: #9C2729;
                color: #fff;
                border: none;
                border-radius: 2px;
                padding: 2px 5px;
            }
            #day i{
                font-weight: normal;
            }
            @media (max-width: 327px) {
                .input-phone-login span{
                    font-size: 17px;
                }
                .input-phone-login input[name=phone]{
                    width: 72%;
                }
            }
            @media (max-width: 435px) {
                .info-number button{
                    padding: 5px 5px 5px 6px;
                    width: 80px;
                    margin-top: -15px;
                }

            }
            @media (max-width: 600px) {
                .info-number-button{
                    position: absolute;
                    right: 13px;
                }

            }
			.coming_soon{
				background-color: #8E8080 !important;
				border: none;
				border-bottom: 4px solid #736869 !important;
			}
        </style>
        <script lang="text/javascript">
            $(function() {
                $(".input-phone-login").hide();
                $(".shadow").hide();
                $(".pupop").click(function() {
                    $(".input-phone-login").css("opacity", 1);
                    $(".input-phone-login").fadeIn();
                    $(".shadow").fadeIn();
                    $('.content').hide();
                });
                $(".shadow").click(function() {
                    $(".input-phone-login").animate({
                        opacity: '0'
                    }, 200, function() {
                        $(".input-phone-login").hide();
                        $(".shadow").fadeOut();
                        $('.content').fadeIn();
                    });
                })
                $("input[name=phone]").click(function() {
                    $(this).val("");
                })
				//$(".setup-game").addClass('coming_soon').parent().attr('href','#');

            });
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
        <script>
            function myCopy(code) {
                window.external.notify("action=copy&value=" + code);
            }

            function WPCallback(json) {
                window.external.notify(json);
            }
        </script>
    </head>
    <body>
        <div class="shadow">

        </div>
        <div style="width: 100%; height: 1px"></div>
      
        <div class="content">

            <div class="info-number">
                <img  src="/assets/images/share.jpg"/>
                <div class="info-number-content">
                    <div class="info-number-title">
                        <span id="title">Share Facebook nhận Giftcode Khủng</span>
                        <span id="day"><i>04/04/2015</i></span>
                    </div>
                    <?php if(empty($game)){ ?>
                    <div class="info-number-button" >
                        <!--<a href="urlscheme://action=share&url=http%3a%2f%2fmt.mobo.vn&image=http%3A%2F%2Fm-app.mobo.vn%2Fassets%2Fmt%2Fimages%2Fshare8t.png&message=Giftcode+MOBO+-+Kho+giftcode+l%e1%bb%9bn+nh%e1%ba%a5t+Vi%e1%bb%87t+Nam+s%e1%ba%bd+t%e1%ba%b7ng+b%e1%ba%a1n+ngay+giftcode+Phi%c3%aan+B%e1%ba%a3n+M%e1%bb%9bi+tr%e1%bb%8b+gi%c3%a1+2.000.000+VN%c4%90+ch%e1%bb%89+v%e1%bb%9bi+thao+t%c3%a1c+share+Fanpage+c%e1%bb%a7a+MOBO&name=Giftcode+MOBO+-+Kho+giftcode+l%e1%bb%9bn+nh%e1%ba%a5t+Vi%e1%bb%87t+Nam+s%e1%ba%bd+t%e1%ba%b7ng+b%e1%ba%a1n+ngay+giftcode+Phi%c3%aan+B%e1%ba%a3n+M%e1%bb%9bi+tr%e1%bb%8b+gi%c3%a1+2.000.000+VN%c4%90+ch%e1%bb%89+v%e1%bb%9bi+thao+t%c3%a1c+share+Fanpage+c%e1%bb%a7a+MOBO&caption=Giftcode+MOBO+-+Kho+giftcode+l%e1%bb%9bn+nh%e1%ba%a5t+Vi%e1%bb%87t+Nam+s%e1%ba%bd+t%e1%ba%b7ng+b%e1%ba%a1n+ngay+giftcode+Phi%c3%aan+B%e1%ba%a3n+M%e1%bb%9bi+tr%e1%bb%8b+gi%c3%a1+2.000.000+VN%c4%90+ch%e1%bb%89+v%e1%bb%9bi+thao+t%c3%a1c+share+Fanpage+c%e1%bb%a7a+MOBO&description=Giftcode+MOBO+-+Kho+giftcode+l%e1%bb%9bn+nh%e1%ba%a5t+Vi%e1%bb%87t+Nam+s%e1%ba%bd+t%e1%ba%b7ng+b%e1%ba%a1n+ngay+giftcode+Phi%c3%aan+B%e1%ba%a3n+M%e1%bb%9bi+tr%e1%bb%8b+gi%c3%a1+2.000.000+VN%c4%90+ch%e1%bb%89+v%e1%bb%9bi+thao+t%c3%a1c+share+Fanpage+c%e1%bb%a7a+MOBO&event_share_id=game">
                            <button class="input-phone">Share</button>
                        </a>-->
                        <?php echo $LikeShare->my_share("http://mt.mobo.vn", 
                                         "http://m-app.mobo.vn/assets/mt/images/share8t.png", 
                                         "Giftcode MOBO - Kho giftcode lớn nhất Việt Nam sẽ tặng bạn ngay giftcode Phiên Bản Mới trị giá 2.000.000 VNĐ chỉ với thao tác share Fanpage của MOBO", 
                                         "Giftcode MOBO - Kho giftcode lớn nhất Việt Nam sẽ tặng bạn ngay giftcode Phiên Bản Mới trị giá 2.000.000 VNĐ chỉ với thao tác share Fanpage của MOBO", 
                                         "Giftcode MOBO - Kho giftcode lớn nhất Việt Nam sẽ tặng bạn ngay giftcode Phiên Bản Mới trị giá 2.000.000 VNĐ chỉ với thao tác share Fanpage của MOBO", 
                                         "Giftcode MOBO - Kho giftcode lớn nhất Việt Nam sẽ tặng bạn ngay giftcode Phiên Bản Mới trị giá 2.000.000 VNĐ chỉ với thao tác share Fanpage của MOBO", 
                                         "game") ?>
                    </div>
                    <?php } ?>
                </div>
            </div>
            <a href="#">
				<img width="100%" src="/assets/mt/images/share8t.png" /></a>
            <div class="gift-code">
                 Code: <?php
                       if (!empty($game))
                           echo $LikeShare->my_Copy($game);
                else
                    echo "Chưa Nhận";
                ?>
            </div>
            <hr>


           
            <div class="space"></div>

        </div>
    </body>
</html>
