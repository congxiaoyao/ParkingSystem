
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.name').val();
        var password = $(this).find('#password').val();
        var ensurnpassword = $(this).find('#ensurnpassword').val();
        var tagid = $(this).find('#tagid').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('#name').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('#password').focus();
            });
            return false;
        }
        if(ensurnpassword == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '164px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('#ensurepassword').focus();
            });
            return false;
        }
        if(tagid == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '232px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('#tagid').focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});




function isCorrect(){
    var password = $(document).find('#password').val();
    var ensurepassword = $(document).find('#ensurepassword').val();
    if (password!==null) {
        if (ensurepassword !== password) {
            $(document).find('.error').fadeOut('fast', function(){
                $('.error').css('top', '164px');
            });
            $(document).find('.error').fadeIn('fast');
        } 
    } else if(password===null){
        $(document).find('.error').fadeOut('fast', function(){
                $('.error').css('top', '96px');
            });
        $(document).find('.error').fadeIn('fast', function(){
                $('.error').parent().find('#password').focus();
            });
    }
}
    

