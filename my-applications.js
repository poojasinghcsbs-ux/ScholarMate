const applications = [
  { id:'tata', name:'Tata Undergraduate Scholarship', provider:'Tata Trusts', logo:'assets/images/logo-tata-blue.png', level:'Undergraduate', type:'Merit-based', applied:'02 Sep 2025', deadline:'10 Oct 2025', status:'review', statusText:'Under Review', action:'View Details', stages:['Application Submitted','Under Review','Result Awaited'], current:1, dates:['02 Sep','05 Sep'] },
  { id:'reliance', name:'Reliance Foundation Scholarship', provider:'Reliance Foundation', logo:'assets/images/logo-reliance.png', level:'Undergraduate', type:'Need-based', applied:'25 Aug 2025', deadline:'12 Nov 2025', status:'shortlisted', statusText:'Shortlisted', action:'View Details', stages:['Application Submitted','Under Review','Result Awaited'], current:1, dates:['25 Aug','28 Aug'] },
  { id:'sbi', name:'SBI Asha Scholarship', provider:'State Bank of India', logo:'assets/images/logo-sbi.png', level:'Postgraduate', type:'Need-based', applied:'15 Aug 2025', deadline:'15 Nov 2025', status:'not-selected', statusText:'Not Selected', action:'View Details', stages:['Application Submitted','Under Review','Not Selected'], current:2, dates:['15 Aug','20 Aug','01 Sep'] },
  { id:'inspire', name:'INSPIRE Scholarship', provider:'Department of Science & Technology', logo:'assets/images/logo-india.svg', level:'Undergraduate', type:'Research', applied:'10 Aug 2025', deadline:'25 Nov 2025', status:'saved', statusText:'Application Saved', action:'Continue Application', stages:['Application Saved','Under Review','Result Awaited'], current:0, dates:['10 Aug'] },
  { id:'adani', name:'Adani Foundation Scholarship', provider:'Adani Foundation', logo:'assets/images/logo-adani.png', level:'Undergraduate', type:'Merit-based', applied:'05 Aug 2025', deadline:'30 Nov 2025', status:'saved', statusText:'Saved for Later', action:'View Details', stages:['Application Saved','Under Review','Result Awaited'], current:0, dates:['05 Aug'] }
];

let activeFilter = 'all';
let timer;
const $ = selector => document.querySelector(selector);
const hasSession = () => Boolean(localStorage.getItem('scholarMateSession'));

function showToast(message) {
  const toast = $('#toast');
  toast.textContent = message;
  toast.classList.add('show');
  clearTimeout(timer);
  timer = setTimeout(() => toast.classList.remove('show'), 2600);
}

function statusClass(status) { return status === 'not-selected' ? 'not-selected' : status; }

function cardMarkup(app) {
  const lineType = app.status === 'not-selected' ? 'fail' : app.current === app.stages.length - 1 ? 'done' : 'good';
  const stages = app.stages.map((stage, index) => {
    const state = index < app.current ? 'done' : index === app.current ? (app.status === 'not-selected' ? 'failed' : app.current === 0 && app.status === 'saved' ? 'current' : 'current') : '';
    const icon = state === 'done' ? '<i class="fa-solid fa-check"></i>' : state === 'failed' ? '<i class="fa-solid fa-xmark"></i>' : index === app.current && app.status === 'review' ? '<i class="fa-regular fa-hourglass-half"></i>' : '';
    const date = app.dates[index] ? `<small>${app.dates[index]}</small>` : '';
    return `<div class="step ${state}"><span class="dot">${icon}</span><strong>${stage}</strong>${date}</div>`;
  }).join('');
  const mainButton = app.action === 'Continue Application' ? 'fill' : 'outline';
  return `<article class="application-card" data-status="${app.status}" data-name="${app.name.toLowerCase()} ${app.provider.toLowerCase()}">
    <img class="provider-logo ${app.id === 'inspire' ? 'inspire' : ''}" src="${app.logo}" alt="${app.provider} logo">
    <div class="provider"><h3>${app.name}</h3><p>${app.provider}</p><div class="tags"><span>${app.level}</span><span>${app.type}</span><span>All India</span></div><div class="dates"><span><i class="fa-regular fa-calendar"></i>Applied on: ${app.applied}</span><span><i class="fa-regular fa-calendar"></i>Deadline: ${app.deadline}</span></div></div>
    <div class="progress ${lineType}">${stages}</div>
    <div class="card-actions"><span class="status ${statusClass(app.status)}">${app.statusText}</span><button class="${mainButton}" data-action="${app.action}" data-name="${app.name}">${app.action} <i class="fa-solid fa-arrow-right"></i></button><button class="more" aria-label="More options"><i class="fa-solid fa-ellipsis-vertical"></i></button></div>
  </article>`;
}

function renderApps() {
  const term = $('#search').value.trim().toLowerCase();
  const sort = $('#sort').value;
  let list = [...applications];
  if (sort === 'name') list.sort((a,b) => a.name.localeCompare(b.name));
  if (sort === 'deadline') list.sort((a,b) => a.deadline.localeCompare(b.deadline));
  $('#application-list').innerHTML = list.map(cardMarkup).join('');
  document.querySelectorAll('.application-card').forEach(card => {
    const matchesTab = activeFilter === 'all' || card.dataset.status === activeFilter || (activeFilter === 'saved' && card.dataset.status === 'saved');
    const matchesSearch = !term || card.dataset.name.includes(term);
    card.classList.toggle('hidden', !(matchesTab && matchesSearch));
  });
}

function renderStats() {
  const values = [
    ['fa-regular fa-file-lines','wine','5','Total Applications','You have applied so far'],
    ['fa-solid fa-circle-check','green','2','Submitted','Under review'],
    ['fa-regular fa-hourglass-half','blue','1','Under Review','Waiting for response'],
    ['fa-solid fa-circle-xmark','red','1','Not Selected','Keep going!'],
    ['fa-regular fa-bookmark','yellow','1','Shortlisted','Great progress!']
  ];
  $('#stats').innerHTML = values.map(v => `<article class="stat"><span class="stat-icon ${v[1]}"><i class="${v[0]}"></i></span><div><b>${v[2]}</b><small>${v[3]}</small><p>${v[4]}</p></div></article>`).join('');
}

function renderTimeline() {
  const events = [
    ['green','fa-solid fa-check','02 Sep 2025','Application Submitted',''],
    ['blue','fa-regular fa-hourglass-half','05 Sep 2025','Under Review','Your application is being reviewed by the scholarship provider.'],
    ['grey','','Result Awaited',"You'll be notified once the result is announced.", '']
  ];
  $('#timeline').innerHTML = events.map(event => `<div class="timeline-item"><span class="timeline-dot ${event[0]}">${event[1] ? `<i class="${event[1]}"></i>` : ''}</span><b>${event[2]}</b><p>${event[3]}</p></div>`).join('');
}

function initialise() {
  const profile = JSON.parse(localStorage.getItem('scholarMateProfile') || '{}');
  $('#student-name').textContent = hasSession() ? (profile.name || 'Student') : 'Guest Student';
  $('#student-role').textContent = hasSession() ? 'Student' : 'Browse scholarships';
  $('#guest-state').hidden = hasSession();
  $('#application-content').hidden = !hasSession();
  if (!hasSession()) return;
  renderStats(); renderTimeline(); renderApps();
  document.querySelectorAll('.tabs button').forEach(button => button.addEventListener('click', () => {
    document.querySelector('.tabs .selected').classList.remove('selected');
    button.classList.add('selected'); activeFilter = button.dataset.filter; renderApps();
  }));
  $('#search').addEventListener('input', renderApps);
  $('#sort').addEventListener('change', renderApps);
  $('#application-list').addEventListener('click', event => {
    const button = event.target.closest('[data-action]');
    if (!button) return;
    showToast(button.dataset.action === 'Continue Application' ? `Opening ${button.dataset.name} application form.` : `${button.dataset.name} details opened.`);
  });
  $('#reminder').addEventListener('click', () => showToast('Deadline reminder has been added.'));
}
document.addEventListener('DOMContentLoaded', initialise);
