$(() => {
    const gravatar = $("[data-gravatar]");
    gravatar.each(function () {
        $(this).css("border-radius", "50%");
        let emailMD5 = $(this).attr("data-gravatar");
        let prefix = "https://cdn.v2ex.com/gravatar/";
        let suffix = ".jpg?s=";
        let size = [20, 40, 80, 160, 200];
        let level = parseInt($(this).attr("data-gravatar-size"));
        if (level > 4 || level < 0 || isNaN(level)) level = 3;
        $(this).attr("src", prefix + emailMD5 + suffix + size[level])
    })
})