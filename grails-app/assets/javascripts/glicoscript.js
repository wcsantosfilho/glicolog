// glicoscript.js

$(document).ready(function() {
    
    // Ativa o DatePicker (Calendário) para o campo Data do formulário
    
    var date_input=$('input[name="dataRegistro"]'); //our date input has the name "date"
    var options={
        dateFormat: 'dd/mm/yy',
        todayHighlight: true,
        autoclose: true,
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
        dayNamesMin: [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab" ],
        maxDate: "+0d"
      };
    date_input.datepicker(options);
    
    
  
    // Define uma máscara para o campo Hora do formulário
    $('input[name="horaRegistro"]').mask('99:99');

    // Conforme a opção escolhida no Tipo de Registro, mostra a seção do formulário respectiva
    var myFunction = function() {
        var v1 = $("input:radio[name ='tipoRegistro']:checked").val()
        switch(v1) {
            case 'Glicemia':
                $('#grupoGlicemia').show();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Glicemia');
                break;
            case 'Insulina':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').show();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Insulina');
                break;
            case 'Refeicao':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').show();
                $('#grupoAtivFisica').hide();
                $('input.formTipoLadoDireito').val('Refeicao');
                break;
            case 'AtivFisica':
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').show();
                $('input.formTipoLadoDireito').val('AtivFisica');
                break;
            default:
                $('#grupoGlicemia').hide();
                $('#grupoInsulina').hide();
                $('#grupoRefeicao').hide();
                $('#grupoAtivFisica').hide();
                break;
         }
    }
    
    // Conforme a opção escolhida no Tipo de Registro, mostra a seção do formulário respectiva
    // Seta evento no carregamento da página para mostrar o form quando do re-carregamento (em caso de erro de preenchimento, por exemplo)
    $(document).on ("ready", myFunction);
    // Chama a função para as mudanças no seletor de radio do tipo do registro
    $('input[type=radio][name=tipoRegistro]').on ( "change", myFunction);
    
    
    // Copia os valores do campos Data e Hora da parte esquerda do formulario para os campos da parte direita
    $('.botaoFormLadoDireito').click(function() {
        $('input.formDataLadoDireito').val($('#dataRegistro').val());
        $('input.formHoraLadoDireito').val($('#horaRegistro').val());
    });

});