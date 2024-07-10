    $(document).ready(function () {
  $(".btn-delete").on("click", function (e) {
    e.preventDefault();
    let link = $(this);
    let fileName = link.attr("fileName");
    $("#yesBtn").prop("href", link.prop("href"));
    $("#confirmText").html("Do you want to delete the File: <strong>" + fileName + "</strong>?");
    $("#confirmModal").modal('show');
  });
});
