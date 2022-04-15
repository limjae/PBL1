  // 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
        // 사용자가 내용을 올바르게 입력하였는지 확인합니다.
        function isValidContents(contents) {
            if (contents == '') {
                alert('내용을 입력해주세요');
                return false;
            }
            if (contents.trim().length > 140) {
                alert('공백 포함 140자 이하로 입력해주세요');
                return false;
            }
            return true;
        }

       // 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
       // 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
        function editPost(id) {
            showEdits(id);
            let title = $(`#${id}-title`).text().trim();
            let username = $(`#${id}-username`).text().trim();
            let contents = $(`#${id}-contents`).text().trim();
            $(`#${id}-title-edit`).val(title);
            $(`#${id}-username-edit`).val(username);
            $(`#${id}-textarea`).val(contents);
        }

        function showEdits(id) {
            $(`#${id}-title-edit`).show();
            $(`#${id}-username-edit`).show();
            $(`#${id}-editarea`).show();
            $(`#${id}-submit`).show();
            $(`#${id}-delete`).show();

            $(`#${id}-metadata`).hide();
            $(`#${id}-contents`).hide();
            $(`#${id}-edit`).hide();
        }
        $(document).ready(function () {
            // HTML 문서를 로드할 때마다 실행합니다.
            getMessages();
        })

        // 메모를 불러와서 보여줍니다.
        function getMessages() {
            // 1. 기존 메모 내용을 지웁니다.
            $('#cards-box').empty();
            // 2. 메모 목록을 불러와서 HTML로 붙입니다.
            $.ajax({
                type: 'GET',
                url: '/api/posting',
                success: function (response) {
                    for (let i = 0; i < response.length; i++) {
                        let message = response[i];
                        let id = message['id'];
                        let title = message['title'];
                        let username = message['username'];
                        let contents = message['contents'];
                        let modifiedAt = message['modifiedAt'];
                        console.log(message);
                        addHTML(id, title, username, contents, modifiedAt);
                    }
                }
            })
        }

        // 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
        function addHTML(id, title, username, contents, modifiedAt) {
            // 1. HTML 태그를 만듭니다.
            let tempHtml = `<div class="card">
                <!-- date/username 영역 -->
                <div id="${id}-metadata" class="metadata">
                    <div id="${id}-title" class="title">
                        ${title}
                    </div>
                    <div id="${id}-username" class="username">
                        ${username}
                    </div>
                    <div class="date">
                        ${modifiedAt}
                    </div>
                </div>
                <!-- contents 조회/수정 영역-->
                <div class="contents">
                    <div id="${id}-contents" class="text">
                        ${contents}
                    </div>
                    <div id="${id}-editarea" class="edit">
                        <label for="${id}-title-edit">글 제목</label><input type="text" id="${id}-title-edit">
                        <label for="${id}-username-edit">글 작성자</label><input type="text" id="${id}-username-edit">
                        <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                    </div>
                </div>
                <!-- 버튼 영역-->
                <div class="footer">
                    <img id="${id}-edit" class="icon-start-edit" src="images/edit.png" alt="" onclick="editPost('${id}')">
                    <img id="${id}-delete" class="icon-delete" src="images/delete.png" alt="" onclick="deleteOne('${id}')">
                    <img id="${id}-show-commment" class="icon-show-commment" src="images/chat-square-text.svg" alt="" onclick="showComment('${id}')">
                    <img id="${id}-hide-commment" class="icon-hide-commment" src="images/chat-square-text.svg" alt="" onclick="hideComment('${id}')">
                    <img id="${id}-submit" class="icon-end-edit" src="images/done.png" alt="" onclick="submitEdit('${id}')">
                </div>
            </div>`;
            // 2. #cards-box 에 HTML을 붙인다.
            $('#cards-box').append(tempHtml);
        }

        // 메모를 생성합니다.
        function writePost() {
            // 1. 작성한 메모를 불러옵니다.
            let contents = $('#contents').val();

            // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
            if (isValidContents(contents) == false) {
                return;
            }
            // 3. 작성한 작성자를 불러온다
            let title = $('#title').val();
            let username = $('#username').val();



            // 4. 전달할 data JSON으로 만듭니다.
            let data = {'title':title, 'username': username, 'contents': contents};

            // 5. POST /api/posting 에 data를 전달합니다.
            $.ajax({
                type: "POST",
                url: "/api/posting",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    alert('메시지가 성공적으로 작성되었습니다.');
                    window.location.reload();
                }
            });
        }

        // 메모를 수정합니다.
        function submitEdit(id) {
            // 1. 작성 대상 메모의 username과 contents 를 확인합니다.
            let title = $(`#${id}-title-edit`).val().trim();
            let username = $(`#${id}-username-edit`).val().trim();
            let contents = $(`#${id}-textarea`).val().trim();

            // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
            if (isValidContents(contents) == false) {
                return;
            }

            // 3. 전달할 data JSON으로 만듭니다.
            let data = {'title':title, 'username': username, 'contents': contents};

            // 4. PUT /api/posting/{id} 에 data를 전달합니다.
            $.ajax({
                type: "PUT",
                url: `/api/posting/${id}`,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (response) {
                    alert('메시지 변경에 성공하였습니다.');
                    window.location.reload();
                }
            });
        }

        // 메모를 삭제합니다.
        function deleteOne(id) {
            // 1. DELETE /api/posting/{id} 에 요청해서 메모를 삭제합니다.
            $.ajax({
                type: "DELETE",
                url: `/api/posting/${id}`,
                success: function (response) {
                    alert('메시지 삭제에 성공하였습니다.');
                    window.location.reload();
                }
            })
        }