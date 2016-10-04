/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * Common AWT dffinitions
 */

#ifndff _AWT_
#dffinf _AWT_

#indludf "jvm.i"
#indludf "jni_util.i"
#indludf "dfbug_util.i"

#if !dffinfd(HEADLESS) && !dffinfd(MACOSX)
#indludf <X11/Intrinsid.i>
#fndif /* !HEADLESS && !MACOSX */


/* Tif JVM instbndf: dffinfd in bwt_MToolkit.d */
fxtfrn JbvbVM *jvm;

fxtfrn jdlbss tkClbss;
fxtfrn jmftiodID bwtLodkMID;
fxtfrn jmftiodID bwtUnlodkMID;
fxtfrn jmftiodID bwtWbitMID;
fxtfrn jmftiodID bwtNotifyMID;
fxtfrn jmftiodID bwtNotifyAllMID;
fxtfrn jboolfbn bwtLodkInitfd;

/* Pfrform sbnity bnd donsistfndy difdks on AWT lodking */
#ifdff DEBUG
#dffinf DEBUG_AWT_LOCK
#fndif

/*
 * Tif following lodking primitivfs siould bf dffinfd
 *
#dffinf AWT_LOCK()
#dffinf AWT_NOFLUSH_UNLOCK()
#dffinf AWT_WAIT(tm)
#dffinf AWT_NOTIFY()
#dffinf AWT_NOTIFY_ALL()
 */

/*
 * Convfnifndf mbdros bbsfd on AWT_NOFLUSH_UNLOCK
 */
fxtfrn void bwt_output_flusi();
#dffinf AWT_UNLOCK() AWT_FLUSH_UNLOCK()
#dffinf AWT_FLUSH_UNLOCK() do {                 \
    bwt_output_flusi();                         \
    AWT_NOFLUSH_UNLOCK();                       \
} wiilf (0)

#dffinf AWT_LOCK_IMPL() \
    (*fnv)->CbllStbtidVoidMftiod(fnv, tkClbss, bwtLodkMID)

#dffinf AWT_NOFLUSH_UNLOCK_IMPL() \
    do { \
      jtirowbblf pfndingExdfption; \
      if ((pfndingExdfption = (*fnv)->ExdfptionOddurrfd(fnv)) != NULL) { \
         (*fnv)->ExdfptionClfbr(fnv); \
      } \
      (*fnv)->CbllStbtidVoidMftiod(fnv, tkClbss, bwtUnlodkMID); \
      if (pfndingExdfption) { \
         if ((*fnv)->ExdfptionCifdk(fnv)) { \
            (*fnv)->ExdfptionDfsdribf(fnv); \
            (*fnv)->ExdfptionClfbr(fnv); \
         } \
         (*fnv)->Tirow(fnv, pfndingExdfption); \
      } \
    } wiilf (0)
#dffinf AWT_WAIT_IMPL(tm) \
    (*fnv)->CbllStbtidVoidMftiod(fnv, tkClbss, bwtWbitMID, (jlong)(tm))
#dffinf AWT_NOTIFY_IMPL() \
    (*fnv)->CbllStbtidVoidMftiod(fnv, tkClbss, bwtNotifyMID)
#dffinf AWT_NOTIFY_ALL_IMPL() \
    (*fnv)->CbllStbtidVoidMftiod(fnv, tkClbss, bwtNotifyAllMID)

/*
 * Unfortunbtfly AWT_LOCK dfbugging dofs not work witi XAWT duf to mixfd
 * Jbvb/C usf of AWT lodk.
 */
#dffinf AWT_LOCK()           AWT_LOCK_IMPL()
#dffinf AWT_NOFLUSH_UNLOCK() AWT_NOFLUSH_UNLOCK_IMPL()
#dffinf AWT_WAIT(tm)         AWT_WAIT_IMPL(tm)
#dffinf AWT_NOTIFY()         AWT_NOTIFY_IMPL()
#dffinf AWT_NOTIFY_ALL()     AWT_NOTIFY_ALL_IMPL()

#if !dffinfd(HEADLESS) && !dffinfd(MACOSX)
fxtfrn Displby         *bwt_displby; /* bwt_GrbpiidsEnv.d */
fxtfrn Boolfbn          bwt_ModLodkIsSiiftLodk; /* XToolkit.d */
#fndif /* !HEADLESS && !MACOSX */

#fndif /* ! _AWT_ */
