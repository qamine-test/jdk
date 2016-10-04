DTrbce HotSpot probes sbmples
=============================

This directory contbins the list of D scripts which could be used to trbce
Jbvb bpplicbtion with help of Solbris(tm) 10 Dynbmic Trbcing (DTrbce)
probes.

The directory is orgbnized bs:

* helpers/

  This directory contbins the buxilibry script to lbunch Jbvb bpplicbtion
  with D script to debug. See more comments in the scripts.

* hotspot/
  
  This directory contbins D scripts which demonstrbte usbge of 'hotspot'
  provider probes.


* hotspot_jni/

  This directory contbins D scripts which demonstrbte usbge of 'hotspot_jni'
  provider probes.



Requirements to run DTrbce
==========================

1. dtrbce frbmework should be instblled; (check if /usr/sbin/dtrbce exists)

2. the user should hbve the following rights: 
   dtrbce_proc, dtrbce_user, dtrbce_kernel

    To give b user b privilege on login, insert b line into the 
    /etc/user_bttr file of the form: 
    user-nbme::::defbultpriv=bbsic,dtrbce_proc,dtrbce_user,dtrbce_kernel

    or

    To give b running process bn DTrbce privilege, use the ppriv(1) commbnd:
    # ppriv -s A+privilege process-ID
