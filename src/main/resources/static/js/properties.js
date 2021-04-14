$(document).ready(
        function () {

            $('#modal-update').on('show.bs.modal', function (event) {
                var trow = $(event.relatedTarget);
                var id = trow.data('propertyid');
                var accountid = trow.data('propertyaccountid');
                var typeid = trow.data('propertytypeid');
                var name = trow.data('propertyname');
                var address = trow.data('propertyaddress');
                var city = trow.data('propertycity');
                var state = trow.data('propertystate');
                var country = trow.data('propertycountry');
                var postcode = trow.data('propertypostcode');
                var latitude = trow.data('propertylatitude');
                var longitude = trow.data('propertylongitude');
                var phone = trow.data('propertyphone');
                var mobile = trow.data('propertymobile');
                var email = trow.data('propertyemail');
                var web = trow.data('propertyweb');
                var firstname = trow.data('propertyfirstname');
                var lasttname = trow.data('propertylasttname');
                var cutoffhour = trow.data('propertycutoffhour');
                var vatrate = trow.data('propertyvatrate');

                var modal = $(this);



                modal.find('#modal-update-label').text('Edit property');
                modal.find('.modal-body form').attr('action', '/properties/' + id + '/update');

                modal.find('.modal-body #id').val(id);
                modal.find('.modal-body #accountId').val(accountid);
                modal.find('.modal-body #type').val(typeid);
                modal.find('.modal-body #name').val(name);

                modal.find('.modal-body #address').val(address);

                modal.find('.modal-body #city').val(city);

                modal.find('.modal-body #state').val(state);
                modal.find('.modal-body #country').val(country);
                modal.find('.modal-body #stpostcodeate').val(postcode);
                modal.find('.modal-body #latitude').val(latitude);
                modal.find('.modal-body #longitude').val(longitude);
                modal.find('.modal-body #phone').val(phone);
                modal.find('.modal-body #mobile').val(mobile);
                modal.find('.modal-body #email').val(email);
                modal.find('.modal-body #web').val(web);
                modal.find('.modal-body #contactFirstName').val(firstname);
                modal.find('.modal-body #contactLastName').val(lasttname);
                modal.find('.modal-body #cutOffHour').val(cutoffhour);
                modal.find('.modal-body #vatRate').val(vatrate);



            });

        });
