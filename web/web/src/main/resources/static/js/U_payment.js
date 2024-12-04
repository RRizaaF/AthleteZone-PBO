// Tampilkan formulir Mastercard saat halaman dimuat
$(document).ready(function () {
    // Tampilkan form Mastercard dan sembunyikan form lainnya
    $('#mastercardForm').show();
    $('#transferForm, #danaForm').hide();

    // Tambahkan style aktif pada Mastercard
    $('#mastercardOption').addClass('active');

    // Event listener untuk klik pada Mastercard
    $('#mastercardOption').on('click', function () {
        $('#mastercardForm').show();
        $('#transferForm, #danaForm').hide();

        // Highlight pilihan aktif
        $('#mastercardOption').addClass('active');
        $('#transferOption, #danaOption').removeClass('active');
    });

    // Event listener untuk klik pada Transfer
    $('#transferOption').on('click', function () {
        $('#transferForm').show();
        $('#mastercardForm, #danaForm').hide();

        // Highlight pilihan aktif
        $('#transferOption').addClass('active');
        $('#mastercardOption, #danaOption').removeClass('active');
    });

    // Event listener untuk klik pada Dana
    $('#danaOption').on('click', function () {
        $('#danaForm').show();
        $('#mastercardForm, #transferForm').hide();

        // Highlight pilihan aktif
        $('#danaOption').addClass('active');
        $('#mastercardOption, #transferOption').removeClass('active');
    });
});
