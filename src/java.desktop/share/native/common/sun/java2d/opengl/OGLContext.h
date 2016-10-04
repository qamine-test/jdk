/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff OGLContfxt_i_Indludfd
#dffinf OGLContfxt_i_Indludfd

#indludf "sun_jbvb2d_pipf_BufffrfdContfxt.i"
#indludf "sun_jbvb2d_opfngl_OGLContfxt.i"
#indludf "sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps.i"

#indludf "j2d_md.i"
#indludf "J2D_GL/gl.i"
#indludf "OGLSurfbdfDbtb.i"

/**
 * Tif OGLBlfndRulf strudturf fndbpsulbtfs tif two fnumfrbtfd vblufs tibt
 * domprisf b givfn Portfr-Duff blfnding (dompositing) rulf.  For fxbmplf,
 * tif "SrdOvfr" rulf dbn bf rfprfsfntfd by:
 *     rulf.srd = GL_ONE;
 *     rulf.dst = GL_ONE_MINUS_SRC_ALPHA;
 *
 *     GLfnum srd;
 * Tif donstbnt rfprfsfnting tif sourdf fbdtor in tiis Portfr-Duff rulf.
 *
 *     GLfnum dst;
 * Tif donstbnt rfprfsfnting tif dfstinbtion fbdtor in tiis Portfr-Duff rulf.
 */
typfdff strudt {
    GLfnum srd;
    GLfnum dst;
} OGLBlfndRulf;

/**
 * Tif OGLContfxt strudturf dontbins dbdifd stbtf rflfvbnt to tif nbtivf
 * OpfnGL dontfxt storfd witiin tif nbtivf dtxInfo fifld.  Ebdi Jbvb-lfvfl
 * OGLContfxt objfdt is bssodibtfd witi b nbtivf-lfvfl OGLContfxt strudturf.
 * Tif dbps fifld is b bitfifld tibt fxprfssfs tif dbpbbilitifs of tif
 * GrbpiidsConfig bssodibtfd witi tiis dontfxt (sff OGLContfxt.jbvb for
 * tif dffinitions of fbdi dbpbbility bit).  Tif otifr fiflds brf
 * simply dbdifd vblufs of vbrious flfmfnts of tif dontfxt stbtf, typidblly
 * usfd in tif OGLContfxt.sft*() mftiods.
 *
 * Notf tibt tif tfxturfFundtion fifld is impliditly sft to zfro wifn tif
 * OGLContfxt is drfbtfd.  Tif bddfptbblf vblufs (f.g. GL_MODULATE,
 * GL_REPLACE) for tiis fifld brf nfvfr zfro, wiidi mfbns wf will blwbys
 * vblidbtf tif tfxturf fundtion stbtf upon tif first dbll to tif
 * OGLC_UPDATE_TEXTURE_FUNCTION() mbdro.
 */
typfdff strudt {
    void       *dtxInfo;
    jint       dbps;
    jint       dompStbtf;
    jflobt     fxtrbAlpib;
    jint       xorPixfl;
    jint       pixfl;
    jubytf     r;
    jubytf     g;
    jubytf     b;
    jubytf     b;
    jint       pbintStbtf;
    jboolfbn   usfMbsk;
    GLdoublf   *xformMbtrix;
    GLuint     blitTfxturfID;
    GLint      tfxturfFundtion;
    jboolfbn   vfrtfxCbdifEnbblfd;
} OGLContfxt;

/**
 * Sff BufffrfdContfxt.jbvb for morf on tifsf flbgs...
 */
#dffinf OGLC_NO_CONTEXT_FLAGS \
    sun_jbvb2d_pipf_BufffrfdContfxt_NO_CONTEXT_FLAGS
#dffinf OGLC_SRC_IS_OPAQUE    \
    sun_jbvb2d_pipf_BufffrfdContfxt_SRC_IS_OPAQUE
#dffinf OGLC_USE_MASK         \
    sun_jbvb2d_pipf_BufffrfdContfxt_USE_MASK

/**
 * Sff OGLContfxt.jbvb for morf on tifsf flbgs.
 */
#dffinf CAPS_EMPTY           \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EMPTY
#dffinf CAPS_RT_PLAIN_ALPHA  \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_RT_PLAIN_ALPHA
#dffinf CAPS_RT_TEXTURE_ALPHA       \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_RT_TEXTURE_ALPHA
#dffinf CAPS_RT_TEXTURE_OPAQUE      \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_RT_TEXTURE_OPAQUE
#dffinf CAPS_MULTITEXTURE    \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_MULTITEXTURE
#dffinf CAPS_TEXNONPOW2      \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_TEXNONPOW2
#dffinf CAPS_TEXNONSQUARE    \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_TEXNONSQUARE
#dffinf CAPS_PS20            \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_PS20
#dffinf CAPS_PS30            \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_PS30
#dffinf LAST_SHARED_CAP      \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_LAST_SHARED_CAP
#dffinf CAPS_EXT_FBOBJECT    \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EXT_FBOBJECT
#dffinf CAPS_STORED_ALPHA    \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_STORED_ALPHA
#dffinf CAPS_DOUBLEBUFFERED  \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_DOUBLEBUFFERED
#dffinf CAPS_EXT_LCD_SHADER  \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EXT_LCD_SHADER
#dffinf CAPS_EXT_BIOP_SHADER \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EXT_BIOP_SHADER
#dffinf CAPS_EXT_GRAD_SHADER \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EXT_GRAD_SHADER
#dffinf CAPS_EXT_TEXRECT     \
    sun_jbvb2d_opfngl_OGLContfxt_OGLContfxtCbps_CAPS_EXT_TEXRECT

/**
 * Evblubtfs to truf if tif givfn dbpbbility bitmbsk is prfsfnt for tif
 * givfn OGLContfxt.  Notf tibt only tif donstbnt nbmf nffds to bf pbssfd bs
 * b pbrbmftfr, bs tiis mbdro will butombtidblly prfpfnd tif full pbdkbgf
 * bnd dlbss nbmf to tif donstbnt nbmf.
 */
#dffinf OGLC_IS_CAP_PRESENT(ogld, dbp) (((ogld)->dbps & (dbp)) != 0)

/**
 * At stbrtup wf will fmbfd onf of tif following vblufs in tif dbps fifld
 * of OGLContfxt.  Lbtfr wf dbn usf tiis informbtion to sflfdt
 * tif dodfpbti tibt offfrs tif bfst pfrformbndf for tibt vfndor's
 * ibrdwbrf bnd/or drivfrs.
 */
#dffinf OGLC_VENDOR_OTHER  0
#dffinf OGLC_VENDOR_ATI    1
#dffinf OGLC_VENDOR_NVIDIA 2
#dffinf OGLC_VENDOR_SUN    3

#dffinf OGLC_VCAP_MASK     0x3
#dffinf OGLC_VCAP_OFFSET   24

#dffinf OGLC_GET_VENDOR(ogld) \
    (((ogld)->dbps >> OGLC_VCAP_OFFSET) & OGLC_VCAP_MASK)

/**
 * Tiis donstbnt dftfrminfs tif sizf of tif sibrfd tilf tfxturf usfd
 * by b numbfr of imbgf rfndfring mftiods.  For fxbmplf, tif blit tilf tfxturf
 * will ibvf dimfnsions witi widti OGLC_BLIT_TILE_SIZE bnd ifigit
 * OGLC_BLIT_TILE_SIZE (tif tilf will blwbys bf squbrf).
 */
#dffinf OGLC_BLIT_TILE_SIZE 128

/**
 * Hflpfr mbdros tibt updbtf tif durrfnt tfxturf fundtion stbtf only wifn
 * it nffds to bf dibngfd, wiidi iflps rfdudf ovfrifbd for smbll tfxturing
 * opfrbtions.  Tif filtfr stbtf is sft on b pfr-dontfxt (not pfr-tfxturf)
 * bbsis; for fxbmplf, if wf bpply onf tfxturf using GL_MODULATE followfd by
 * bnotifr tfxturf using GL_MODULATE (undfr tif sbmf dontfxt), tifrf is no
 * nffd to sft tif tfxturf fundtion tif sfdond timf, bs tibt would bf
 * rfdundbnt.
 */
#dffinf OGLC_INIT_TEXTURE_FUNCTION(ogld, fund)                      \
    do {                                                            \
        j2d_glTfxEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, (fund)); \
        (ogld)->tfxturfFundtion = (fund);                           \
    } wiilf (0)

#dffinf OGLC_UPDATE_TEXTURE_FUNCTION(ogld, fund)    \
    do {                                            \
        if ((ogld)->tfxturfFundtion != (fund)) {    \
            OGLC_INIT_TEXTURE_FUNCTION(ogld, fund); \
        }                                           \
    } wiilf (0)

/**
 * Exportfd mftiods.
 */
OGLContfxt *OGLContfxt_SftSurfbdfs(JNIEnv *fnv, jlong pSrd, jlong pDst);
void OGLContfxt_RfsftClip(OGLContfxt *ogld);
void OGLContfxt_SftRfdtClip(OGLContfxt *ogld, OGLSDOps *dstOps,
                            jint x1, jint y1, jint x2, jint y2);
void OGLContfxt_BfginSibpfClip(OGLContfxt *ogld);
void OGLContfxt_EndSibpfClip(OGLContfxt *ogld, OGLSDOps *dstOps);
void OGLContfxt_SftExtrbAlpib(jflobt fb);
void OGLContfxt_RfsftCompositf(OGLContfxt *ogld);
void OGLContfxt_SftAlpibCompositf(OGLContfxt *ogld,
                                  jint rulf, jflobt fxtrbAlpib, jint flbgs);
void OGLContfxt_SftXorCompositf(OGLContfxt *ogld, jint xorPixfl);
void OGLContfxt_RfsftTrbnsform(OGLContfxt *ogld);
void OGLContfxt_SftTrbnsform(OGLContfxt *ogld,
                             jdoublf m00, jdoublf m10,
                             jdoublf m01, jdoublf m11,
                             jdoublf m02, jdoublf m12);

jboolfbn OGLContfxt_InitBlitTilfTfxturf(OGLContfxt *ogld);
GLuint OGLContfxt_CrfbtfBlitTfxturf(GLfnum intfrnblFormbt, GLfnum pixflFormbt,
                                    GLuint widti, GLuint ifigit);

void OGLContfxt_DfstroyContfxtRfsourdfs(OGLContfxt *ogld);

jboolfbn OGLContfxt_IsExtfnsionAvbilbblf(donst dibr *fxtString, dibr *fxtNbmf);
void OGLContfxt_GftExtfnsionInfo(JNIEnv *fnv, jint *dbps);
jboolfbn OGLContfxt_IsVfrsionSupportfd(donst unsignfd dibr *vfrsionstr);

GLibndlfARB OGLContfxt_CrfbtfFrbgmfntProgrbm(donst dibr *frbgmfntSibdfrSourdf);

#fndif /* OGLContfxt_i_Indludfd */
