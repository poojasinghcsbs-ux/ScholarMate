const session=JSON.parse(localStorage.getItem('scholarMateSession')||'null');
const profile=JSON.parse(localStorage.getItem('scholarMateProfile')||'{}');
const account=JSON.parse(localStorage.getItem('scholarMateAccount')||'{}');
const needed=['education','course','grade','familyIncome','domicile'];
const profileReady=Boolean(session)&&needed.every(key=>String(profile[key]||'').trim());
const avatar='data:image/svg+xml,%3Csvg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"%3E%3Crect width="100" height="100" fill="%23d5d7de"/%3E%3Ccircle cx="50" cy="38" r="18" fill="white"/%3E%3Cpath d="M15 92c5-25 20-37 35-37s30 12 35 37" fill="white"/%3E%3C/svg%3E';
document.getElementById('avatar').src=profile.photo||avatar;
if(session){document.getElementById('userName').textContent=profile.fullName||account.name||'Student';document.getElementById('userRole').textContent='Student'}
const notifications=session?Number(localStorage.getItem('scholarMateNotifications')||0):0;
document.getElementById('bellBadge').textContent=notifications;document.getElementById('sideBadge').textContent=notifications;
const check=document.getElementById('profileCheck');
if(profileReady){check.innerHTML='<i class="fa-solid fa-circle-check"></i><div><b>Your eligibility is ready to check</b><p>Based on the details you submitted, you can continue with this application.</p><a href="#apply">View eligibility details <i class="fa-solid fa-arrow-right"></i></a></div>';check.classList.add('ready')}
const toast=(text)=>{const item=document.getElementById('toast');item.textContent=text;item.classList.add('show');setTimeout(()=>item.classList.remove('show'),2400)};
document.getElementById('apply').onclick=()=>!session?toast('Please log in before applying for a scholarship.'):!profileReady?toast('Complete Academic and Eligibility details to apply.'):toast('Application started successfully.');
document.getElementById('save').onclick=()=>!session?toast('Please log in to save scholarships.'):toast('Scholarship saved to your profile.');
document.getElementById('bell').onclick=()=>session?toast('You’re all caught up!'):toast('Log in to receive notifications.');
document.querySelector('[data-logout]').onclick=()=>localStorage.removeItem('scholarMateSession');

const overview=document.querySelector('.content').innerHTML;
const tabPanels={
  overview,
  eligibility:`<article class="tab-panel"><h2>Eligibility Criteria</h2><p>Use these public guidelines to understand whether this scholarship may be relevant. Your final eligibility is checked only after you submit your Academic and Eligibility details.</p><ul class="criteria"><li><i class="fa-solid fa-circle-check"></i>Applicant should be an Indian citizen.</li><li><i class="fa-solid fa-circle-check"></i>Applicant must be enrolled in an undergraduate programme.</li><li><i class="fa-solid fa-circle-check"></i>Selection is based on academic merit and supporting documents.</li><li><i class="fa-solid fa-circle-check"></i>Family income and other criteria are verified during application review.</li></ul><a class="profile-link" href="profile.html">Complete profile to check your eligibility <i class="fa-solid fa-arrow-right"></i></a></article>`,
  benefits:`<article class="tab-panel"><h2>Scholarship Benefits</h2><div class="benefit-grid"><div><i class="fa-solid fa-coins"></i><b>Financial support</b><span>Up to ₹50,000 per year towards education expenses.</span></div><div><i class="fa-solid fa-arrows-rotate"></i><b>Renewal opportunity</b><span>May be renewed based on academic performance.</span></div><div><i class="fa-solid fa-user-graduate"></i><b>Mentorship support</b><span>Guidance to help students continue their education.</span></div><div><i class="fa-solid fa-globe"></i><b>All India access</b><span>Open to eligible students across India.</span></div></div></article>`,
  documents:`<article class="tab-panel"><h2>Documents Required</h2><p>Keep these documents ready before starting an application. Upload options appear only after you log in and complete your profile.</p><ul class="document-list"><li><i class="fa-regular fa-file-lines"></i> Latest marksheet / academic record</li><li><i class="fa-regular fa-id-card"></i> Government photo ID</li><li><i class="fa-solid fa-file-invoice"></i> Income certificate, if applicable</li><li><i class="fa-solid fa-building-columns"></i> Bank account details</li></ul></article>`,
  process:`<article class="tab-panel"><h2>Application Process</h2><ol class="process"><li><b>Complete your profile</b><span>Add accurate personal, academic and eligibility information.</span></li><li><b>Check eligibility</b><span>Review the criteria and your profile-based match.</span></li><li><b>Prepare documents</b><span>Keep all required documents ready for submission.</span></li><li><b>Submit application</b><span>Review your application carefully and submit online.</span></li></ol></article>`,
  dates:`<article class="tab-panel"><h2>Important Dates</h2><ul class="timeline dates"><li><b>Applications Open</b><small>1 Aug 2025</small></li><li><b>Application Deadline</b><small>10 Oct 2025</small></li><li><b>Shortlisting</b><small>Nov 2025</small></li><li><b>Result Announcement</b><small>Dec 2025</small></li></ul></article>`
};
document.querySelectorAll('[data-tab]').forEach((tab)=>tab.onclick=()=>{
  document.querySelectorAll('[data-tab]').forEach((item)=>item.classList.toggle('active',item===tab));
  document.querySelector('.content').innerHTML=tabPanels[tab.dataset.tab];
});
