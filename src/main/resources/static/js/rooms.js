$(document).ready(
        function () {
          

            $('#modal-update').on('show.bs.modal', function (event) {
                
                
                var trow = $(event.relatedTarget);
                var propertyId = trow.data('propertyid');
                var id = trow.data('roomid');
                var name = trow.data('roomname');
                var maxGuests = trow.data('maxguests');
                var quantity = trow.data('quantity');
                var room = trow.data('room');
                var price = trow.data('price');
                var modal = $(this);



                $.ajax({
                    url: 'http://localhost:8080/api/v1/rooms/' + id + '/units',
                    type: 'get',
                    data: '',
                    success: function (response) {
                        // Add response in Modal body

                       var unitstring = [];

                        console.log(response._embedded.units);


                        $.each(response._embedded.units, function (k, v) {
                            console.log(v.name);
                            if(v.info===null){
                                unitstring.push(v.name+'|');
                            } else{
                            unitstring.push(v.name+'|'+v.info);
                        }
                        });



                        unitstring.sort(function (a, b) {
                            var a1 = a[1], b1 = b[1];
                            if (a1 == b1)
                                return 0;
                            return a1 > b1 ? 1 : -1;
                        });
                        var units = unitstring.join('\n');

                        $('.modal-body #unitstring').html(units);


                    }
                });

                modal.find('#modal-update-label').text('Edit room');
                modal.find('.modal-body form').attr('action', '/rooms/' + id + '/update');
                modal.find('.modal-body #propertyId').val(propertyId);
                modal.find('.modal-body #id').val(id);
                modal.find('.modal-body #name').val(name);

                modal.find('.modal-body #maxGuests').val(maxGuests);

                modal.find('.modal-body #quantity').val(quantity);

                modal.find('.modal-body #price').val(price);


            });
            $("#modal-update-button").on('click', function () {
                $("textarea#unitstring").val().replace("\n", "\\n");
            }
            );



            // AJAX request


        });
