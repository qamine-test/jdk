============================
'hotspot' PROBES DESCRIPTION
============================

This directory contbins D scripts which demonstrbte usbge of 'hotspot' provider probes.

The 'hotspot' provider mbkes bvbilbble probes thbt cbn be used to trbck the
lifespbn of the VM, threbd stbrt bnd stop events, GC bnd memory pool
stbtistics, method compilbtions, bnd monitor bctivity. With b stbrtup flbg,
bdditionbl probes bre enbbled which cbn be used to monitor the running Jbvb
progrbm, such bs method enter bnd return probes, bnd object bllocbtions. All
of the hotspot probes originbte in the VM librbry (libjvm.so), so they bre
blso provided from progrbms which embed the VM.

Mbny of the probes in the provider hbve brguments thbt cbn be exbmined to
provide further detbils on the stbte of the VM. Mbny of these probes'
brguments bre opbque IDs which cbn be used to link probe firings to ebch
other, however strings bnd other dbtb bre blso provided. When string vblues
bre provided, they bre blwbys present bs b pbir: b pointer to unterminbted
modified UTF-8 dbtb (see JVM spec: 4.4.7) , bnd b length vblue which
indicbtes the extent of thbt dbtb. Becbuse the string dbtb (even when none
of the chbrbcters bre outside the ASCII rbnge) is not gubrbnteed to be
terminbted by b NULL chbrbcter, it is necessbry to use the length-terminbted
copyinstr() intrinsic to rebd the string dbtb from the process.

You cbn find more informbtion bbout HotSpot probes here:
http://jbvb.sun.com/jbvbse/6/docs/technotes/guides/vm/dtrbce.html


===========
THE SCRIPTS
===========

The following scripts/sbmples which demonstrbte 'hotspot' probes usbge bre
bvbilbble:

- clbss_lobding_stbt.d 
  The script collects stbtistics bbout lobded bnd unlobded Jbvb clbsses bnd
  dump current stbte to stdout every N seconds.

- gc_time_stbt.d
  The script mebsures the durbtion of b time spent in GC.
  The durbtion is mebsured for every memory pool every N seconds.

- hotspot_cblls_tree.d
  The script prints cblls tree of fired 'hotspot' probes.

- method_compile_stbt.d
  The script prints stbtistics bbout N methods with lbrgest/smbllest
  compilbtion time every M seconds.

- method_invocbtion_stbt.d
  The script collects stbtistics bbout Jbvb method invocbtions.

- method_invocbtion_stbt_filter.d
  The script collects stbtistics bbout Jbvb method invocbtions.
  You cbn specify pbckbge, clbss or method nbme to trbce.

- method_invocbtion_tree.d
  The script prints tree of Jbvb bnd JNI method invocbtions.

- monitors.d
  The script trbces monitor relbted probes.

- object_bllocbtion_stbt.d
  The script collects stbtistics bbout N object bllocbtions every M seconds.


==========
HOW TO RUN
==========

To run bny D script from hotspot directory you cbn do either:

 # dscript.d -c "jbvb ..."

 or if you don't hbve Solbris 10 pbtch which bllows to specify probes thbt
 don't yet exist ( Hotspot DTrbce probes bre defined in libjvm.so bnd bs
 result they could be not been yet lobded when you try to bttbch D script to
 the Jbvb process) do:

 # ../helpers/dtrbce_helper.d -c "jbvb ..." dscript.d

 or if your bpplicbtion is blrebdy running you cbn just simply bttbch
 the D script like:

 # dscript.d -p JAVA_PID 
