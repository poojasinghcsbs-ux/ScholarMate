/* Show personal dashboard data only after the student has actually created it. */
(() => {
  const hasSession=Boolean(localStorage.getItem('scholarMateSession'));
  const applications=JSON.parse(localStorage.getItem('scholarMateApplications')||'[]');
  const notificationCount=Number(localStorage.getItem('scholarMateNotifications')||'0');
  const deadlineList=document.getElementById('deadlineList');
  const notificationList=document.getElementById('notificationList');
  const set=(id,value)=>{const element=document.getElementById(id);if(element)element.textContent=value};
  if(!hasSession){
    set('appliedCount','0');set('reviewCount','0');set('shortlistedCount','0');set('deadlineCount','0');set('bellCount','0');set('sideNotice','0');
    document.getElementById('applicationTable').innerHTML='<div class="empty-state"><i class="fa-regular fa-folder-open"></i><b>No applications yet</b><span>Log in and apply for scholarships to see your journey here.</span></div>';
    deadlineList.innerHTML='<div class="empty-state compact"><i class="fa-regular fa-calendar"></i><b>No upcoming deadlines</b><span>Your applied-scholarship deadlines will appear here.</span></div>';
    notificationList.innerHTML='<div class="empty-state compact"><i class="fa-regular fa-bell"></i><b>No notifications yet</b><span>Updates will appear after you start your scholarship journey.</span></div>';
    return;
  }
  const review=applications.filter(item=>item.status==='Under Review').length;
  const shortlisted=applications.filter(item=>item.status==='Shortlisted').length;
  set('appliedCount',applications.length);set('reviewCount',review);set('shortlistedCount',shortlisted);set('bellCount',notificationCount);set('sideNotice',notificationCount);
  if(!applications.length){document.getElementById('applicationTable').innerHTML='<div class="empty-state"><i class="fa-regular fa-folder-open"></i><b>No applications yet</b><span>Browse recommendations and apply to start tracking your progress.</span></div>';deadlineList.innerHTML='<div class="empty-state compact"><i class="fa-regular fa-calendar"></i><b>No upcoming deadlines</b><span>Applied scholarship deadlines show here.</span></div>';set('deadlineCount','0')}
  if(!notificationCount){notificationList.innerHTML='<div class="empty-state compact"><i class="fa-regular fa-bell"></i><b>You’re all caught up</b><span>New scholarship updates will appear here.</span></div>'}
})();
