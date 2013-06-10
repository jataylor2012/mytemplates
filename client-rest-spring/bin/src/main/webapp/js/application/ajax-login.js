function ajaxSessionTimeout()
{
    window.location.href = 'login.html';
}
 
!function( $ )
{
    $.ajaxSetup({
        statusCode: 
        {
            901: ajaxSessionTimeout
        }
    });
}(window.jQuery);