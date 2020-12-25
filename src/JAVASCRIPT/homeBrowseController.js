class HomeBrowseController {

    constructor() {
    }

    initBrowseView() {
        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",
            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",
            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=",
            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Caseificio/showProduct.jsp"
        ];
        let selectedMicroServiceEndpoint = microServiceEndpoints[3];
        let controller = this;
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + selectedMicroServiceEndpoint);
        });
        this.showMessageStatus("black", "Requesting data from service: " + selectedMicroServiceEndpoint);
    }

    renderGUI(data) {
        let controller = this;
        let staticHtml = $("#product-row-template").html();
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            row = row.replace(/{nome_prodotto}/ig, obj.nome_prodotto);
            row = row.replace(/{provenienza}/ig, obj.provenienza);
            row = row.replace(/{tempo_preparazione}/ig, obj.tempo_preparazione);
            row = row.replace(/{prezzo}/ig, obj.prezzo);
            row = row.replace(/{descrizione}/ig, obj.descrizione);
            $('#product-rows').append(row);
        });
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
        }
    }

    showMessageStatus(color, message) {
        $("#request-status").css("color", color)
            .html(message);
    }
}