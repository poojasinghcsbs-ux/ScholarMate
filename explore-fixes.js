(() => {
  const logos = {
    'Tata Undergraduate Scholarship': 'logo-tata.png',
    'Reliance Foundation Scholarship': 'logo-reliance.png',
    'SBI Asha Scholarship': 'logo-sbi.png',
    'INSPIRE Scholarship': 'logo-india.svg',
    'Adani Foundation Scholarship': 'logo-adani.png',
    'Central Sector Scholarship': 'logo-india.svg'
  };
  const profile = JSON.parse(localStorage.getItem('scholarMateProfile') || '{}');
  const signedIn = Boolean(localStorage.getItem('scholarMateSession'));
  const requiredForMatch = ['education', 'course', 'grade', 'familyIncome', 'domicile'];
  const canCalculateMatch = signedIn && requiredForMatch.every((key) => String(profile[key] || '').trim());
  const scholarshipInfo = {
    'Tata Undergraduate Scholarship': { field: 'engineering', type: 'merit-based' },
    'Reliance Foundation Scholarship': { field: 'engineering', type: 'need-based' },
    'SBI Asha Scholarship': { field: 'medical', type: 'need-based' },
    'INSPIRE Scholarship': { field: 'science', type: 'merit-based' },
    'Adani Foundation Scholarship': { field: 'engineering', type: 'need-based' },
    'Central Sector Scholarship': { field: 'science', type: 'government' }
  };

  function eligibilityScore(title) {
    const info = scholarshipInfo[title] || {};
    const interests = `${profile.course || ''} ${profile.careerInterest || ''}`.toLowerCase();
    const preference = String(profile.scholarshipType || '').toLowerCase();
    const grade = Number.parseFloat(profile.grade);
    const income = Number.parseFloat(profile.familyIncome);
    let score = 55;
    score += interests.includes(info.field) ? 18 : 5;
    score += Number.isFinite(grade) ? (grade >= 80 || grade >= 8 ? 12 : 6) : 0;
    score += preference === info.type ? 8 : 0;
    if (info.type === 'need-based') score += Number.isFinite(income) && income <= 500000 ? 12 : 3;
    return Math.min(score, 96);
  }

  function swapLogos() {
    document.querySelectorAll('.card').forEach((card) => {
      const title = card.querySelector('h3')?.textContent;
      const logoBox = card.querySelector('.provider');
      const logo = logos[title];
      if (!title || !logoBox || !logo || logoBox.dataset.logo) return;

      logoBox.dataset.logo = 'true';
      logoBox.innerHTML = `<img src="${logo}" alt="${title} logo">`;
      logoBox.querySelector('img').onerror = () => {
        logoBox.textContent = title.includes('INSPIRE') ? 'INSPIRE' : title.split(' ')[0];
      };

      const match = card.querySelector('.match');
      if (match && canCalculateMatch) match.textContent = `${eligibilityScore(title)}% Match`;
      if (match && !canCalculateMatch) {
        match.textContent = 'Complete profile';
        match.classList.add('profile-needed');
      }
    });
    document.querySelectorAll('[data-apply]').forEach((button) => {
      button.onclick = () => {
        window.location.href = `scholarship-details.html?scholarship=${encodeURIComponent(button.dataset.apply)}`;
      };
    });
  }

  const topQuote = document.querySelector('.quote');
  const bottomQuote = document.querySelector('.step');
  if (topQuote) topQuote.src = 'explore-top-quote.jpeg';
  if (bottomQuote) {
    bottomQuote.innerHTML = '<img src="explore-bottom-quote.jpeg" alt="Scholarship opportunity quote">';
  }

  const popular = document.querySelector('.popular');
  if (popular && ![...popular.querySelectorAll('button')].some((button) => button.textContent === 'Need-based')) {
    const needBased = document.createElement('button');
    needBased.type = 'button';
    needBased.textContent = 'Need-based';
    needBased.addEventListener('click', () => {
      document.getElementById('searchInput').value = 'Need-based';
      document.getElementById('searchForm').requestSubmit();
    });
    const meritBased = [...popular.querySelectorAll('button')].find((button) => button.textContent === 'Merit-based');
    meritBased?.insertAdjacentElement('afterend', needBased);
  }

  swapLogos();
  const cardArea = document.getElementById('cards');
  if (cardArea) new MutationObserver(swapLogos).observe(cardArea, { childList: true, subtree: true });
})();
