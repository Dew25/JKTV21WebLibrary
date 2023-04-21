
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Новый читатель</h1>
        <div class="row d-flex justify-content-center ">
            <div class="card m-2 border-0" style="width: 35rem;">
                <form action="createReader" method="POST">
                    <div class="mb-3 row">
                        <label for="inputFirstname" class="col-sm-3 col-form-label">Имя:</label>
                        <div class="col-sm-9">
                          <input type="text" class="w-100" id="inputFirstname" name="firstname" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputLastname" class="col-sm-3 col-form-label">Фамилия:</label>
                        <div class="col-sm-9">
                          <input type="text" class="w-100" id="inputLastname" name="lastname" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputPhone" class="col-sm-3 col-form-label">Телефон:</label>
                        <div class="col-sm-9">
                          <input type="text" class="w-100" id="inputPhone" name="phone" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-12 d-flex justify-content-end">
                            <input class="btn btn-primary" type="submit" value="Добавить">
                        </div>
                    </div>
                </form>
            </div>
    
        </div>
        
    
