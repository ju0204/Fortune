
function showContext(idToShow) {
    const ids = ["contextLove", "contextMoney", "contextCareer", "contextHealth"];

    ids.forEach(id => {
        document.getElementById(id).style.display = (id === idToShow) ? "inline" : "none";
    });
}

function openPopup() {
    document.getElementById('qrPopup').classList.remove('hidden');
}

function closePopup() {
    document.getElementById('qrPopup').classList.add('hidden');
}
//https나 localhost 환경에서만 작동 가능
function copyToClipboard() {
    const text = document.getElementById('copyText').value;

    // 클립보드에 복사
    navigator.clipboard.writeText(text)
      .then(() => {
        alert("클립보드에 복사되었습니다!");
      })
      .catch(err => {
        console.error('클립보드 복사 실패:', err);
      });
  }

const selector = document.getElementById('type_seletor');
const tarotDiv = document.getElementById('present-tarot');
const zodiacDiv = document.getElementById('present-zodiac');

  selector.addEventListener('change', function () {
    if (this.value === 'tarot') {
      tarotDiv.style.display = 'block';
      zodiacDiv.style.display = 'none';
    } else if (this.value === 'zodiac') {
      tarotDiv.style.display = 'none';
      zodiacDiv.style.display = 'block';
    }
  });