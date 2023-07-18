// 페이지 로드 시 초기 입력 창 이벤트 처리
let editor;
document.addEventListener("DOMContentLoaded", function () {
    // 에디터
    var editorElement = document.querySelector('#editor');
    if (editorElement) {
        ClassicEditor
            .create(editorElement, {
                language: 'ko' //언어설정
            })
            .then(contents => {
                editor = contents; // #contents에 있는 값을 theEditor에 넣어놓는다.
            })
            .catch(error => {
                console.error(error);
            });
    }
    if(modifyPostDTO) {
        //토글처리
        var radioButtons = document.querySelectorAll('input[name="is_eligibility"]');
        radioButtons.forEach(function (radio) {
            radio.addEventListener("change", toggleEligibilityInfo);
        });

        //행 추가
        var addButton = document.querySelector(".add-button");
        addButton.addEventListener("click", addInputRow);

        // 폼(submit) 이벤트 처리 - 생성, 수정
        var form = document.querySelector('#postForm');
        form.addEventListener('submit', handleSubmit);
    }

    //삭제 처리
    var deletePostById = document.getElementById('deletePost');
    deletePostById.addEventListener("click",deletePost);
});

// 예매 가능 여부 정보 토글 함수
function toggleEligibilityInfo() {
    var radio = document.querySelector('input[name="is_eligibility"]:checked');
    var eligibilityInfo = document.getElementById("eligibility_info");
    var reservationSitesContainer = document.getElementById("reservation-sites-container");

    // 예 버튼이 선택된 경우
    if (radio && radio.value === "1") {
        eligibilityInfo.style.display = "block";
    } else {
        eligibilityInfo.style.display = "none";
        reservationSitesContainer.innerHTML = ""; // URL 입력 부분 초기화
        addInputRow(false);
    }
}

// 추가 버튼 클릭 시 새로운 입력 창을 추가하는 함수
function addInputRow(hasVariable) {
    event.preventDefault();
    var container = document.getElementById("reservation-sites-container");
    var row = document.createElement("div");
    row.classList.add("row", "reservation-site-row");

    var siteSelectCol = document.createElement("div");
    siteSelectCol.classList.add("col-md-4");

    var siteSelect = document.createElement("select");
    siteSelect.name = "reservation_site";
    siteSelect.classList.add("form-select", "reservation-site");

    bookingSites.forEach(function (site) {
        var option = document.createElement("option");
        option.value = site.id;
        option.textContent = site.name;
        siteSelect.appendChild(option);
    });

    siteSelectCol.appendChild(siteSelect);

    var urlInputCol = document.createElement("div");
    urlInputCol.classList.add("col-md-6");

    var urlInput = document.createElement("input");
    urlInput.type = "url";
    urlInput.name = "reservation_url";
    urlInput.classList.add("form-control", "reservation-url");

    urlInputCol.appendChild(urlInput);

    var addButtonCol = document.createElement("div");
    addButtonCol.classList.add("col-md-2");

    var addButton = document.createElement("a");
    addButton.href = "#";
    addButton.classList.add("btn","btn-circle", "btn-outline-primary", "btn-sm", "add-button");
    addButton.innerHTML = '<i class="uil uil-plus"></i>';
    addButton.addEventListener("click", addInputRow);

    addButtonCol.appendChild(addButton);

    row.appendChild(siteSelectCol);
    row.appendChild(urlInputCol);
    row.appendChild(addButtonCol);
    container.appendChild(row);

    if (hasVariable) {
        // 기존 입력 창의 추가 버튼을 삭제 버튼으로 변경
        var removeButton = document.createElement("a");
        removeButton.href = "#";
        removeButton.classList.add("btn" ,"btn-circle", "btn-outline-gradient","gradient-1", "btn-sm", "remove-button");
        removeButton.innerHTML = '<span><i class="uil uil-times"></i></span>';
        removeButton.addEventListener("click", removeInputRow);

        var existingAddButton = document.querySelector(".add-button");
        existingAddButton.parentNode.replaceChild(removeButton, existingAddButton);
        removeButton.addEventListener("click", removeInputRow);
    }
}

// 삭제 버튼 클릭 시 해당 입력 창 제거하는 함수
function removeInputRow() {
    event.preventDefault();
    var row = event.target.closest(".reservation-site-row");
    var container = row.parentElement;
    container.removeChild(row);
}

// 전송 제어
function handleSubmit(event) {
    event.preventDefault(); // 기본 동작 방지

    // 전시 정보
    const title = document.getElementById('title').value;
    const exhibitionId = document.getElementById('exhibition_id').value;
    const startDate = document.getElementById('start_date').value;
    const endDate = document.getElementById('end_date').value;
    const ageRestriction = document.getElementById('age_restriction').value;
    const price = document.getElementById('price').value;

    //시작시간 ~ 종료시간 처리
    const startTime = document.getElementById('start_time').value;
    const endTime = document.getElementById('end_time').value;
    const timeRange = startTime + ' ~ ' + endTime;
    const viewingTime = timeRange;

    // 예매 가능 여부
    const isEligibility = document.querySelector('input[name="is_eligibility"]:checked').value;
    const eligibilityDate = document.getElementById('eligibility_date').value;

    // 예매 사이트 정보
    const reservationSites = document.querySelectorAll('.reservation-site-row');
    const bookingLinks = [];

    var postId;
    if (modifyPostDTO && modifyPostDTO.id) {
        postId = modifyPostDTO.id;
    } else {
        postId = null;
    }
    reservationSites.forEach(site => {
        const siteId = site.querySelector('.reservation-site').value;
        const bookingUrl = site.querySelector('.reservation-url').value;

        bookingLinks.push({
            postId: postId, // 이 부분은 서버 처리
            siteId,
            bookingUrl
        });
    });

    // 요약 및 설명
    const summary = document.getElementById('summary').value;
    const originUrl = document.getElementById('orign_url').value;

    // 에디터 생성 및 대기
    var description = document.getElementById('editor').value;
    var PostDTO = {
        id:postId,
        title:title,
        exhibitionId: exhibitionId,
        startDate: startDate,
        endDate: endDate,
        viewingTime: viewingTime,
        ageRestriction: ageRestriction,
        price: price,
        isEligibility: isEligibility,
        eligibilityDate: eligibilityDate,
        bookingLinks: bookingLinks,
        summary: summary,
        originUrl: originUrl,
        description: description
    };

    var url;

    if (modifyPostDTO) { // postDTO가 존재하는 경우
        url = '/artpost/post/' + modifyPostDTO.id + '/edit';
    } else { // postDTO가 존재하지 않는 경우
        url = '/artpost/create-post';
    }

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(PostDTO)
    })
        .then(response => response.json())
        .then(data => {
            window.location.href = data.redirectUrl; // 리다이렉트 처리
        })
        .catch(error => {
            // 오류 처리
        });
}



