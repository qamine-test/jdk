/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

#ifndff _JPLISAGENT_H_
#dffinf _JPLISAGENT_H_

#indludf    <jni.i>
#indludf    <jvmti.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 *  Tif JPLISAgfnt mbnbgfs tif initiblizbtion bll of tif Jbvb progrbmming lbngubgf Agfnts.
 *  It blso supports tif nbtivf mftiod bridgf bftwffn tif JPLIS bnd tif JVMTI.
 *  It mbintbins b singlf JVMTI Env tibt bll JPL bgfnts sibrf.
 *  It pbrsfs dommbnd linf rfqufsts bnd drfbtfs individubl Jbvb bgfnts.
 */


/*
 *  Forwbrd dffinitions
 */
strudt  _JPLISAgfnt;

typfdff strudt _JPLISAgfnt        JPLISAgfnt;
typfdff strudt _JPLISEnvironmfnt  JPLISEnvironmfnt;


/* donstbnts for dlbss nbmfs bnd mftiods nbmfs bnd sudi
    tifsf bll must stby in synd witi Jbvb dodf & intfrfbdfs
*/
#dffinf JPLIS_INSTRUMENTIMPL_CLASSNAME                      "sun/instrumfnt/InstrumfntbtionImpl"
#dffinf JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODNAME         "<init>"
#dffinf JPLIS_INSTRUMENTIMPL_CONSTRUCTOR_METHODSIGNATURE    "(JZZ)V"
#dffinf JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODNAME      "lobdClbssAndCbllPrfmbin"
#dffinf JPLIS_INSTRUMENTIMPL_PREMAININVOKER_METHODSIGNATURE "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"
#dffinf JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODNAME      "lobdClbssAndCbllAgfntmbin"
#dffinf JPLIS_INSTRUMENTIMPL_AGENTMAININVOKER_METHODSIGNATURE "(Ljbvb/lbng/String;Ljbvb/lbng/String;)V"
#dffinf JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODNAME           "trbnsform"
#dffinf JPLIS_INSTRUMENTIMPL_TRANSFORM_METHODSIGNATURE      \
    "(Ljbvb/lbng/ClbssLobdfr;Ljbvb/lbng/String;Ljbvb/lbng/Clbss;Ljbvb/sfdurity/ProtfdtionDombin;[BZ)[B"


/*
 *  Error mfssbgfs
 */
#dffinf JPLIS_ERRORMESSAGE_CANNOTSTART              "prodfssing of -jbvbbgfnt fbilfd"


/*
 *  Our initiblizbtion frrors
 */
typfdff fnum {
  JPLIS_INIT_ERROR_NONE,
  JPLIS_INIT_ERROR_CANNOT_CREATE_NATIVE_AGENT,
  JPLIS_INIT_ERROR_FAILURE,
  JPLIS_INIT_ERROR_ALLOCATION_FAILURE,
  JPLIS_INIT_ERROR_AGENT_CLASS_NOT_SPECIFIED
} JPLISInitiblizbtionError;


strudt _JPLISEnvironmfnt {
    jvmtiEnv *              mJVMTIEnv;              /* tif JVM TI fnvironmfnt */
    JPLISAgfnt *            mAgfnt;                 /* dorrfsponding bgfnt */
    jboolfbn                mIsRftrbnsformfr;       /* indidbtfs if spfdibl fnvironmfnt */
};

strudt _JPLISAgfnt {
    JbvbVM *                mJVM;                   /* ibndlf to tif JVM */
    JPLISEnvironmfnt        mNormblEnvironmfnt;     /* for fvfry tiing but rftrbnsform stuff */
    JPLISEnvironmfnt        mRftrbnsformEnvironmfnt;/* for rftrbnsform stuff only */
    jobjfdt                 mInstrumfntbtionImpl;   /* ibndlf to tif Instrumfntbtion instbndf */
    jmftiodID               mPrfmbinCbllfr;         /* mftiod on tif InstrumfntbtionImpl tibt dofs tif prfmbin stuff (dbdifd to sbvf lots of lookups) */
    jmftiodID               mAgfntmbinCbllfr;       /* mftiod on tif InstrumfntbtionImpl for bgfnts lobdfd vib bttbdi mfdibnism */
    jmftiodID               mTrbnsform;             /* mftiod on tif InstrumfntbtionImpl tibt dofs tif dlbss filf trbnsform */
    jboolfbn                mRfdffinfAvbilbblf;     /* dbdifd bnswfr to "dofs tiis bgfnt support rfdffinf" */
    jboolfbn                mRfdffinfAddfd;         /* indidbtfs if dbn_rfdffinf_dlbssfs dbpbbility ibs bffn bddfd */
    jboolfbn                mNbtivfMftiodPrffixAvbilbblf; /* dbdifd bnswfr to "dofs tiis bgfnt support prffixing" */
    jboolfbn                mNbtivfMftiodPrffixAddfd;     /* indidbtfs if dbn_sft_nbtivf_mftiod_prffix dbpbbility ibs bffn bddfd */
    dibr donst *            mAgfntClbssNbmf;        /* bgfnt dlbss nbmf */
    dibr donst *            mOptionsString;         /* -jbvbbgfnt options string */
};

/*
 * JVMTI fvfnt ibndlfrs
 */

/* VMInit fvfnt ibndlfr. Instbllfd during OnLobd, tifn rfmovfd during VMInit. */
fxtfrn void JNICALL
fvfntHbndlfrVMInit( jvmtiEnv *      jvmtifnv,
                    JNIEnv *        jnifnv,
                    jtirfbd         tirfbd);

/* ClbssFilfLobdHook fvfnt ibndlfr. Instbllfd during VMInit, tifn lfft in plbdf forfvfr. */
fxtfrn void JNICALL
fvfntHbndlfrClbssFilfLobdHook(  jvmtiEnv *              jvmtifnv,
                                JNIEnv *                jnifnv,
                                jdlbss                  dlbss_bfing_rfdffinfd,
                                jobjfdt                 lobdfr,
                                donst dibr*             nbmf,
                                jobjfdt                 protfdtionDombin,
                                jint                    dlbss_dbtb_lfn,
                                donst unsignfd dibr*    dlbss_dbtb,
                                jint*                   nfw_dlbss_dbtb_lfn,
                                unsignfd dibr**         nfw_dlbss_dbtb);

/*
 * Mbin fntry points for tif JPLIS JVMTI bgfnt dodf
 */

/* looks up tif  fnvironmfnt instbndf. rfturns null if tifrf isn't onf */
fxtfrn JPLISEnvironmfnt *
gftJPLISEnvironmfnt(jvmtiEnv * jvmtifnv);

/*  Crfbtfs b nfw JPLIS bgfnt.
 *  Rfturns frror if tif bgfnt dbnnot bf drfbtfd bnd initiblizfd.
 *  Tif JPLISAgfnt* pointfd to by bgfnt_ptr is sft to tif nfw brokfr,
 *  or NULL if bn frror ibs oddurrfd.
 */
fxtfrn JPLISInitiblizbtionError
drfbtfNfwJPLISAgfnt(JbvbVM * vm, JPLISAgfnt **bgfnt_ptr);

/* Adds dbn_rfdffinf_dlbssfs dbpbbility */
fxtfrn void
bddRfdffinfClbssfsCbpbbility(JPLISAgfnt * bgfnt);

/* Add tif dbn_sft_nbtivf_mftiod_prffix dbpbbility */
fxtfrn void
bddNbtivfMftiodPrffixCbpbbility(JPLISAgfnt * bgfnt);

/* Add tif dbn_mbintbin_originbl_mftiod_ordfr dbpbbility (for tfsting) */
fxtfrn void
bddOriginblMftiodOrdfrCbpbbility(JPLISAgfnt * bgfnt);


/* Our JPLIS bgfnt is pbrbllflfd by b Jbvb InstrumfntbtionImpl instbndf.
 * Tiis routinf usfs JNI to drfbtf bnd initiblizfd tif Jbvb instbndf.
 * Rfturns truf if it suddffds, fblsf otifrwisf.
 */
fxtfrn jboolfbn
drfbtfInstrumfntbtionImpl( JNIEnv *        jnifnv,
                           JPLISAgfnt *    bgfnt);


/* during OnLobd pibsf (dommbnd linf pbrsing)
 *  rfdord tif pbrbmftfrs of -jbvbbgfnt
 */
fxtfrn JPLISInitiblizbtionError
rfdordCommbndLinfDbtb(  JPLISAgfnt *    bgfnt,
                        donst dibr *    bgfntClbss,
                        donst dibr *    optionsString );

/* Swbps tif stbrt pibsf fvfnt ibndlfrs out bnd tif livf pibsf fvfnt ibndlfrs in.
 * Also usfd in bttbdi to fnbblfd livf pibsf fvfnt ibndlfrs.
 * Rfturns truf if it suddffds, fblsf otifrwisf.
 */
fxtfrn jboolfbn
sftLivfPibsfEvfntHbndlfrs(  JPLISAgfnt * bgfnt);

/* Lobds tif Jbvb bgfnt bddording to tif blrfbdy prodfssfd dommbnd linf. For fbdi,
 * lobds tif Jbvb bgfnt dlbss, tifn dblls tif prfmbin mftiod.
 * Rfturns truf if bll Jbvb bgfnt dlbssfs brf lobdfd bnd bll prfmbin mftiods domplftf witi no fxdfptions,
 * fblsf otifrwisf.
 */
fxtfrn jboolfbn
stbrtJbvbAgfnt( JPLISAgfnt *    bgfnt,
                JNIEnv *        jnifnv,
                donst dibr *    dlbssnbmf,
                donst dibr *    optionsString,
                jmftiodID       bgfntMbinMftiod);


/* during VMInit prodfssing
 *  tiis is iow tif invodbtion fnginf (dbllbbdk wrbppfr) tflls us to stbrt up bll tif jbvbbgfnts
 */
fxtfrn jboolfbn
prodfssJbvbStbrt(   JPLISAgfnt *    bgfnt,
                    JNIEnv *        jnifnv);

/* on bn ongoing bbsis,
 *  tiis is iow tif invodbtion fnginf (dbllbbdk wrbppfr) tflls us to prodfss b dlbss filf
 */
fxtfrn void
trbnsformClbssFilf(             JPLISAgfnt *            bgfnt,
                                JNIEnv *                jnifnv,
                                jobjfdt                 lobdfr,
                                donst dibr*             nbmf,
                                jdlbss                  dlbssBfingRfdffinfd,
                                jobjfdt                 protfdtionDombin,
                                jint                    dlbss_dbtb_lfn,
                                donst unsignfd dibr*    dlbss_dbtb,
                                jint*                   nfw_dlbss_dbtb_lfn,
                                unsignfd dibr**         nfw_dlbss_dbtb,
                                jboolfbn                is_rftrbnsformfr);

/* on bn ongoing bbsis,
 *  Rfturn tif fnvironmfnt witi tif rftrbnsformbtion dbpbbility.
 *  Crfbtf it if it dofsn't fxist.
 */
fxtfrn jvmtiEnv *
rftrbnsformbblfEnvironmfnt(JPLISAgfnt * bgfnt);

/* on bn ongoing bbsis,
 *  tifsf brf implfmfntbtions of tif Instrumfntbtion sfrvidfs.
 *  Most brf simplf dovfrs for JVMTI bddfss sfrvidfs. Tifsf brf tif guts of tif InstrumfntbtionImpl
 *  nbtivf mftiods.
 */
fxtfrn jboolfbn
isModifibblfClbss(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jdlbss dlbzz);

fxtfrn jboolfbn
isRftrbnsformClbssfsSupportfd(JNIEnv * jnifnv, JPLISAgfnt * bgfnt);

fxtfrn void
sftHbsRftrbnsformbblfTrbnsformfrs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jboolfbn ibs);

fxtfrn void
rftrbnsformClbssfs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jobjfdtArrby dlbssfs);

fxtfrn void
rfdffinfClbssfs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jobjfdtArrby dlbssDffinitions);

fxtfrn jobjfdtArrby
gftAllLobdfdClbssfs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt);

fxtfrn jobjfdtArrby
gftInitibtfdClbssfs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jobjfdt dlbssLobdfr);

fxtfrn jlong
gftObjfdtSizf(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jobjfdt objfdtToSizf);

fxtfrn void
bppfndToClbssLobdfrSfbrdi(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jstring jbrFilf, jboolfbn isBootLobdfr);

fxtfrn void
sftNbtivfMftiodPrffixfs(JNIEnv * jnifnv, JPLISAgfnt * bgfnt, jobjfdtArrby prffixArrby,
                        jboolfbn isRftrbnsformbblf);

#dffinf jvmti(b) b->mNormblEnvironmfnt.mJVMTIEnv

/*
 * A sft of mbdros for insulbting tif JLI mftiod dbllfrs from
 * JVMTI_ERROR_WRONG_PHASE rfturn dodfs.
 */

/* for b JLI mftiod wifrf "blob" is fxfdutfd bfforf simply rfturning */
#dffinf difdk_pibsf_blob_rft(rft, blob)      \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        blob;                                \
        rfturn;                              \
    }

/* for b JLI mftiod wifrf simply rfturning is bfnign */
#dffinf difdk_pibsf_rft(rft)                 \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        rfturn;                              \
    }

/* for b JLI mftiod wifrf rfturning zfro (0) is bfnign */
#dffinf difdk_pibsf_rft_0(rft)               \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        rfturn 0;                            \
    }

/* for b JLI mftiod wifrf rfturning onf (1) is bfnign */
#dffinf difdk_pibsf_rft_1(rft)               \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        rfturn 1;                            \
    }

/* for b dbsf wifrf b spfdifid "blob" must bf rfturnfd */
#dffinf difdk_pibsf_rft_blob(rft, blob)      \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        rfturn (blob);                       \
    }

/* for b JLI mftiod wifrf rfturning fblsf is bfnign */
#dffinf difdk_pibsf_rft_fblsf(rft)           \
    if ((rft) == JVMTI_ERROR_WRONG_PHASE) {  \
        rfturn (jboolfbn) 0;                 \
    }

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */


#fndif
