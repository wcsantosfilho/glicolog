// glicoscript.js

$(function() {
    
    // Ativa o DatePicker (Calendário) para o campo Data do formulário
    
    var date_input=$('input[name="formData"]'); //our date input has the name "date"
    var options={
        dateFormat: 'dd/mm/yy',
        todayHighlight: true,
        autoclose: true,
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
        showButtonPanel: true,
        maxDate: "+0d"
      };
    date_input.datepicker(options);
    
    // Define uma máscara para o campo Hora do formulário
    //$('input[name="formHora"]').mask('00:00');

    // Esconde os formulários para habilitar depois conforme a escolha
    $('#grupoGlicemia').hide();
    $('#grupoInsulina').hide();
    $('#grupoRefeicao').hide();
    $('#grupoAtivFisica').hide();
    
    // Conforme a opção escolhida no Tipo de Registro, mostra a seção do formulário respectiva
    $('input[type=radio][name=formOpcAtiv]').change(function() {
        switch(this.value) {
            case 'Glicemia':
                $('#grupoGlicemia').show();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                break;
            case 'Insulina':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').show();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                break;
            case 'Refeicao':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').show();
                $('#grupoAtivFisica').hide();
                break;
            case 'AtivFisica':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').show();
                break;
            default:
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                break;
         }
    });
    
    // Copia os valores do campos Data e Hora da parte esquerda do formulario para os campos da parte direita
    
    $('.botaoFormLadoDireito').click(function() {
        $('input.formDataLadoDireito').val($('#formData').val());
        $('input.formHoraLadoDireito').val($('#formHora').val());
    });

});

















