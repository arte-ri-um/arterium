// 페이지 로드 시 초기 입력 창 이벤트 처리
document.addEventListener("DOMContentLoaded", function () {
    //만약 postDTO의 값이 있으면 수정모드
    if(modifyPostDTO){
        setInputValues(modifyPostDTO);
    }
});

// 예매 가능 여부 정보 토글 함수
function setInputValues() {
    //일반 input 값 넣기
    document.querySelector("#title").value = modifyPostDTO.title;
    document.querySelector("#age_restriction").value = modifyPostDTO.ageRestriction;
    document.querySelector("#start_date").value = modifyPostDTO.startDate;
    document.querySelector("#end_date").value = modifyPostDTO.endDate;
    document.querySelector("#price").value = modifyPostDTO.price;
    document.querySelector("#orign_url").value = modifyPostDTO.originUrl;
    document.querySelector("#summary").value = modifyPostDTO.summary;
    // document.querySelector(".ck-content").value = modifyPostDTO.description;
    editor.setData(modifyPostDTO.description);
    //시간 파싱
    if (modifyPostDTO.viewingTime) {
        var viewingTime = modifyPostDTO.viewingTime;
        var timeParts = viewingTime.split(" ~ ");
        var startTime = timeParts[0];
        var endTime = timeParts[1];

        document.querySelector('#start_time').value = startTime;
        document.querySelector('#end_time').value = endTime;
    }

    // 전시장정보 선택
    if (modifyPostDTO && exhibitionDTO) {
        var exhibitionId = exhibitionDTO.id;    //숫자
        var selectElement = document.getElementById('exhibition_id');
        Array.from(selectElement.options).forEach(function (option) {
            var optionValue = Number(option.value);  // 숫자로 변환
            if (optionValue === exhibitionId) {
                option.selected = true;
            }
        });
    }
    // 예매 정보 선택
    var isEligibility = modifyPostDTO.isEligibility;
    var yesRadio = document.getElementById('is_eligibility_yes');
    var noRadio = document.getElementById('is_eligibility_no');
    var eligibilityInfo = document.getElementById('eligibility_info');

    if (isEligibility === 1) {
        yesRadio.checked = true;
        eligibilityInfo.style.display = 'block';
        document.querySelector('#eligibility_date').value = modifyPostDTO.eligibilityDate;
        document.querySelector('#reservation-sites-container').innerHTML = '';

        modifyPostDTO.bookingLinks.forEach(function(bookingLink, index) {
            var row = document.createElement('div');
            row.classList.add('row', 'reservation-site-row');

            var col1 = document.createElement('div');
            col1.classList.add('col-md-4');

            var select = document.createElement('select');
            select.name = 'reservation_site';
            select.classList.add('form-select', 'reservation-site');

            bookingSites.forEach(function(site) {
                var option = document.createElement('option');
                option.value = site.id;
                option.text = site.name;
                if (site.id === bookingLink.siteId) {
                    option.selected = true;
                }
                select.appendChild(option);
            });

            col1.appendChild(select);
            row.appendChild(col1);

            var col2 = document.createElement('div');
            col2.classList.add('col-md-6');

            var input = document.createElement('input');
            input.type = 'url';
            input.name = 'reservation_url';
            input.classList.add('form-control', 'reservation-url');
            input.value = bookingLink.bookingUrl;

            col2.appendChild(input);
            row.appendChild(col2);

            var col3 = document.createElement('div');
            col3.classList.add('col-md-2');

            if (index !== modifyPostDTO.bookingLinks.length - 1) {
                var removeButton = document.createElement('a');
                removeButton.href = '#';
                removeButton.classList.add('btn', 'btn-circle', 'btn-outline-gradient', 'gradient-1', 'btn-sm', 'remove-button');
                removeButton.innerHTML = '<span><i class="uil uil-times"></i></span>';
                removeButton.addEventListener('click', removeInputRow);
                col3.appendChild(removeButton);
            } else {
                var addButton = document.createElement('a');
                addButton.href = '#';
                addButton.classList.add('btn', 'btn-circle', 'btn-outline-primary', 'btn-sm', 'add-button');
                addButton.innerHTML = '<i class="uil uil-plus"></i>';
                addButton.addEventListener('click', addInputRow);
                col3.appendChild(addButton);
            }

            row.appendChild(col3);
            document.querySelector('#reservation-sites-container').appendChild(row);
        });
    }else if (isEligibility === 0) {
        noRadio.checked = true;
        eligibilityInfo.style.display = 'none';
    }
}

function deletePost() {
    event.preventDefault();
    var deleteUrl = '/artpost/post/'+deltePostDTO.id+'/delete';
    fetch(deleteUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
        .then(response => response.json())
        .then(data => {
            if (data.redirectUrl) {
                // 리다이렉트할 URL이 존재할 경우 리다이렉트
                window.location.href = data.redirectUrl;
            } else {
                console.error('리다이렉트 URL이 없습니다.');
            }
        })
        .catch(error => {
            // 오류 발생 시 수행할 동작
            console.error('오류가 발생했습니다.', error);
        });
}


