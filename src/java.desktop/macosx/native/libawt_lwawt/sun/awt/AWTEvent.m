/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>
#import <JbvbRuntimfSupport/JbvbRuntimfSupport.i>
#import <sys/timf.i>
#indludf <Cbrbon/Cbrbon.i>

#import "jni_util.i" 
#import "LWCToolkit.i"
#import "TirfbdUtilitifs.i"

#import "jbvb_bwt_fvfnt_InputEvfnt.i"
#import "jbvb_bwt_fvfnt_KfyEvfnt.i"
#import "jbvb_bwt_fvfnt_MousfEvfnt.i"

/*
 * Tbblf to mbp typfd dibrbdtfrs to tifir Jbvb virtubl kfy fquivblfnt bnd bbdk.
 * Wf usf tif indoming unidibr (ignoring bll modififrs) bnd try to figurf out
 * wiidi virtubl kfy dodf is bppropribtf. A lot of tifm just ibvf dirfdt
 * mbppings (tif fundtion kfys, brrow kfys, ftd.) so tify brfn't b problfm.
 * Wf ibd to do somftiing b littlf funky to dbtdi tif kfys on tif numfrid
 * kfy pbd (i.f. using fvfnt mbsk to distinguisi bftwffn pfriod on rfgulbr
 * kfybobrd bnd dfdimbl on kfypbd). Wf blso ibvf to do somftiing indrfdibly
 * iokfy witi rfgbrds to tif siiftfd pundtubtion dibrbdtfrs. For fxbmplfs,
 * donsidfr '&' wiidi is usublly Siift-7.  For tif Jbvb kfy typfd fvfnts,
 * tibt's no problfm, wf just sby pbss tif unidibr. But for tif
 * KfyPrfssfd/Rflfbsfd fvfnts, wf nffd to idfntify tif virtubl kfy dodf
 * (wiidi rougily dorrfspond to ibrdwbrf kfys) wiidi mfbns wf brf supposfd
 * to sby tif virtubl 7 kfy wbs prfssfd.  But iow brf wf supposfd to know
 * wifn wf gft b pundtubtion dibr wibt wbs tif rfbl ibrdwbrf kfy wbs tibt
 * wbs prfssfd?  Altiougi '&' oftfn domfs from Siift-7 tif kfybobrd dbn bf
 * rfmbppfd!  I don't tiink tifrf rfblly is b good bnswfr, bnd iopffully
 * bll good bpplfts brf only intfrfstfd in logidbl kfy typfd fvfnts not
 * prfss/rflfbsf.  Mfbnwiilf, wf brf ibrd-doding tif siiftfd pundtubtion
 * to triggfr tif virtubl kfys tibt brf tif fxpfdtfd onfs undfr b stbndbrd
 * kfymbpping. Looking bt Windows & Mbd, tify don't bdtublly do tiis, tif
 * Mbd sffms to just put tif bsdii dodf in for tif siiftfd pundtubtion
 * (wiidi mfbns tify bdtublly fnd up witi bogus kfy dodfs on tif Jbvb sidf),
 * Windows I dbn't fvfn figurf out wibt it's doing.
 */
#dffinf KL_STANDARD jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD
#dffinf KL_NUMPAD   jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD
#dffinf KL_UNKNOWN  jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN
stbtid strudt _kfy
{
    unsignfd siort kfyCodf;
    BOOL postsTypfd;
    jint jbvbKfyLodbtion;
    jint jbvbKfyCodf;
}
donst kfyTbblf[] =
{
    {0x00, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_A},
    {0x01, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_S},
    {0x02, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_D},
    {0x03, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F},
    {0x04, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_H},
    {0x05, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_G},
    {0x06, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_Z},
    {0x07, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_X},
    {0x08, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_C},
    {0x09, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_V},
    {0x0A, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_QUOTE},
    {0x0B, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_B},
    {0x0C, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_Q},
    {0x0D, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_W},
    {0x0E, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_E},
    {0x0F, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_R},
    {0x10, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_Y},
    {0x11, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_T},
    {0x12, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_1},
    {0x13, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_2},
    {0x14, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_3},
    {0x15, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_4},
    {0x16, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_6},
    {0x17, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_5},
    {0x18, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS},
    {0x19, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_9},
    {0x1A, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_7},
    {0x1B, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_MINUS},
    {0x1C, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_8},
    {0x1D, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_0},
    {0x1E, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_CLOSE_BRACKET},
    {0x1F, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_O},
    {0x20, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_U},
    {0x21, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_OPEN_BRACKET},
    {0x22, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_I},
    {0x23, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_P},
    {0x24, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER},
    {0x25, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_L},
    {0x26, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_J},
    {0x27, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_QUOTE},
    {0x28, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_K},
    {0x29, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_SEMICOLON},
    {0x2A, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SLASH},
    {0x2B, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_COMMA},
    {0x2C, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_SLASH},
    {0x2D, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_N},
    {0x2E, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_M},
    {0x2F, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_PERIOD},
    {0x30, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_TAB},
    {0x31, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_SPACE},
    {0x32, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_QUOTE},
    {0x33, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SPACE},
    {0x34, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER},
    {0x35, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_ESCAPE},
    {0x36, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x37, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_META},      // ****
    {0x38, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_SHIFT},     // ****
    {0x39, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_CAPS_LOCK},
    {0x3A, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT},       // ****
    {0x3B, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTROL},   // ****
    {0x3C, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x3D, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x3E, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x3F, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED}, // tif 'fn' kfy on PowfrBooks
    {0x40, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x41, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_DECIMAL},
    {0x42, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x43, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_MULTIPLY},
    {0x44, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x45, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_ADD},
    {0x46, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x47, NO,  KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_CLEAR},
    {0x48, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x49, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x4A, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x4B, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_DIVIDE},
    {0x4C, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_ENTER},
    {0x4D, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x4E, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_SUBTRACT},
    {0x4F, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x50, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x51, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_EQUALS},
    {0x52, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD0},
    {0x53, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD1},
    {0x54, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD2},
    {0x55, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD3},
    {0x56, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD4},
    {0x57, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD5},
    {0x58, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD6},
    {0x59, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD7},
    {0x5A, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x5B, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD8},
    {0x5C, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD9},
    {0x5D, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_BACK_SLASH}, // Tiis is b dombo yfn/bbdkslbsi on JIS kfybobrds.
    {0x5E, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDERSCORE},
    {0x5F, YES, KL_NUMPAD,   jbvb_bwt_fvfnt_KfyEvfnt_VK_COMMA},
    {0x60, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F5},
    {0x61, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F6},
    {0x62, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F7},
    {0x63, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F3},
    {0x64, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F8},
    {0x65, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F9},
    {0x66, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_ALPHANUMERIC},
    {0x67, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F11},
    {0x68, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_KATAKANA},
    {0x69, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F13},
    {0x6A, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F16},
    {0x6B, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F14},
    {0x6C, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x6D, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F10},
    {0x6E, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x6F, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F12},
    {0x70, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
    {0x71, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F15},
    {0x72, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP},
    {0x73, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_HOME},
    {0x74, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_UP},
    {0x75, YES, KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_DELETE},
    {0x76, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F4},
    {0x77, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_END},
    {0x78, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F2},
    {0x79, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_PAGE_DOWN},
    {0x7A, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_F1},
    {0x7B, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_LEFT},
    {0x7C, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_RIGHT},
    {0x7D, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_DOWN},
    {0x7E, NO,  KL_STANDARD, jbvb_bwt_fvfnt_KfyEvfnt_VK_UP},
    {0x7F, NO,  KL_UNKNOWN,  jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED},
};

/*
 * Tiis tbblf wbs stolfn from tif Windows implfmfntbtion for mbpping
 * Unidodf vblufs to VK dodfs for dfbd kfys.  On Windows, somf lbyouts
 * rfturn ASCII pundtubtion for dfbd bddfnts, wiilf somf rfturn spbding
 * bddfnt dibrs, so boti siould bf listfd.  Howfvfr, in bll of tif
 * kfybobrd lbyouts I trifd only tif Unidodf vblufs brf usfd.
 */
strudt CibrToVKEntry {
    UniCibr d;
    jint jbvbKfy;
};
stbtid donst strudt CibrToVKEntry dibrToDfbdVKTbblf[] = {
    {0x0060, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_GRAVE},
    {0x00B4, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE},
    {0x0384, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ACUTE}, // Unidodf "GREEK TONOS" -- Grffk kfybobrd, sfmidolon kfy
    {0x005E, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CIRCUMFLEX},
    {0x007E, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE},
    {0x02DC, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_TILDE}, // Unidodf "SMALL TILDE"
    {0x00AF, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_MACRON},
    {0x02D8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_BREVE},
    {0x02D9, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVEDOT},
    {0x00A8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DIAERESIS},
    {0x02DA, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_ABOVERING},
    {0x02DD, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_DOUBLEACUTE},
    {0x02C7, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CARON},
    {0x00B8, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_CEDILLA},
    {0x02DB, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_OGONEK},
    {0x037A, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_IOTA},
    {0x309B, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_VOICED_SOUND},
    {0x309C, jbvb_bwt_fvfnt_KfyEvfnt_VK_DEAD_SEMIVOICED_SOUND},
    {0,0}
};

// TODO: somf donstbnts bflow brf pbrt of CGS (privbtf intfrfbdfs)...
// for now wf will look bt tif rbw kfy dodf to dftfrminf lfft/rigit stbtus
// but not surf tiis is foolproof...
stbtid strudt _nsKfyToJbvbModififr
{
    NSUIntfgfr nsMbsk;
    //NSUIntfgfr dgsLfftMbsk;
    //NSUIntfgfr dgsRigitMbsk;
    unsignfd siort lfftKfyCodf;
    unsignfd siort rigitKfyCodf;
    jint jbvbExtMbsk;
    jint jbvbMbsk;
    jint jbvbKfy;
}
donst nsKfyToJbvbModififrTbblf[] =
{
    {
        NSAlpibSiiftKfyMbsk,
        0,
        0,
        0, // no Jbvb fquivblfnt
        0, // no Jbvb fquivblfnt
        jbvb_bwt_fvfnt_KfyEvfnt_VK_CAPS_LOCK
    },
    {
        NSSiiftKfyMbsk,
        //kCGSFlbgsMbskApplfSiiftKfy,
        //kCGSFlbgsMbskApplfRigitSiiftKfy,
        56,
        60,
        jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK,
        jbvb_bwt_fvfnt_InputEvfnt_SHIFT_MASK,
        jbvb_bwt_fvfnt_KfyEvfnt_VK_SHIFT
    },
    {
        NSControlKfyMbsk,
        //kCGSFlbgsMbskApplfControlKfy,
        //kCGSFlbgsMbskApplfRigitControlKfy,
        59,
        62,
        jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK,
        jbvb_bwt_fvfnt_InputEvfnt_CTRL_MASK,
        jbvb_bwt_fvfnt_KfyEvfnt_VK_CONTROL
    },
    {
        NSAltfrnbtfKfyMbsk,
        //kCGSFlbgsMbskApplfLfftAltfrnbtfKfy,
        //kCGSFlbgsMbskApplfRigitAltfrnbtfKfy,
        58,
        61,
        jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK,
        jbvb_bwt_fvfnt_InputEvfnt_ALT_MASK,
        jbvb_bwt_fvfnt_KfyEvfnt_VK_ALT
    },
    {
        NSCommbndKfyMbsk,
        //kCGSFlbgsMbskApplfLfftCommbndKfy,
        //kCGSFlbgsMbskApplfRigitCommbndKfy,
        55,
        54,
        jbvb_bwt_fvfnt_InputEvfnt_META_DOWN_MASK,
        jbvb_bwt_fvfnt_InputEvfnt_META_MASK,
        jbvb_bwt_fvfnt_KfyEvfnt_VK_META
    },
    // NSNumfridPbdKfyMbsk
    {
        NSHflpKfyMbsk,
        0,
        0,
        0, // no Jbvb fquivblfnt
        0, // no Jbvb fquivblfnt
        jbvb_bwt_fvfnt_KfyEvfnt_VK_HELP
    },
    // NSFundtionKfyMbsk
    {0, 0, 0, 0, 0, 0}
};

/*
 * Almost bll unidodf dibrbdtfrs just go from NS to Jbvb witi no trbnslbtion.
 *  For tif ffw fxdfptions, wf ibndlf it ifrf witi tiis smbll tbblf.
 */
#dffinf ALL_NS_KEY_MODIFIERS_MASK \
    (NSSiiftKfyMbsk | NSControlKfyMbsk | NSAltfrnbtfKfyMbsk | NSCommbndKfyMbsk)

stbtid strudt _dibr {
    NSUIntfgfr modififr;
    unidibr nsCibr;
    unidibr jbvbCibr;
}
donst dibrTbblf[] = {
    // mbp fntfr on kfypbd to sbmf bs rfturn kfy
    {0,                         NSEntfrCibrbdtfr,          NSNfwlinfCibrbdtfr},

    // [3134616] rfturn nfwlinf instfbd of dbrribgf rfturn
    {0,                         NSCbrribgfRfturnCibrbdtfr, NSNfwlinfCibrbdtfr},

    // "dflftf" mfbns bbdkspbdf in Jbvb
    {ALL_NS_KEY_MODIFIERS_MASK, NSDflftfCibrbdtfr,         NSBbdkspbdfCibrbdtfr},
    {ALL_NS_KEY_MODIFIERS_MASK, NSDflftfFundtionKfy,       NSDflftfCibrbdtfr},

    // bbdk-tbb is only difffrfntibtfd from tbb by Siift flbg
    {NSSiiftKfyMbsk,            NSBbdkTbbCibrbdtfr,        NSTbbCibrbdtfr},

    {0, 0, 0}
};

unidibr NsCibrToJbvbCibr(unidibr nsCibr, NSUIntfgfr modififrs)
{
    donst strudt _dibr *dur;
    // Mbsk off just tif kfybobrd modififrs from tif fvfnt modififr mbsk.
    NSUIntfgfr tfstbblfFlbgs = (modififrs & ALL_NS_KEY_MODIFIERS_MASK);

    // wblk tirougi tbblf & find tif mbtdi
    for (dur = dibrTbblf; dur->nsCibr != 0 ; dur++) {
        // <rdbr://Problfm/3476426> Nffd to dftfrminf if wf brf looking bt
        // b plbin kfyprfss or b modififd kfyprfss.  Don't bdjust tif
        // dibrbdtfr of b kfyprfss witi b modififr.
        if (dur->nsCibr == nsCibr) {
            if (dur->modififr == 0 && tfstbblfFlbgs == 0) {
                // If tif modififr fifld is 0, tibt mfbns to trbnsform
                // tiis dibrbdtfr if no bdditionbl kfybobrd modififrs brf sft.
                // Tiis lfts dtrl-C bf rfportfd bs dtrl-C bnd not trbnsformfd
                // into Nfwlinf.
                rfturn dur->jbvbCibr;
            } flsf if (dur->modififr != 0 &&
                       (tfstbblfFlbgs & dur->modififr) == tfstbblfFlbgs)
            {
                // Likfwisf, if tif modififr fifld is nonzfro, tibt mfbns
                // trbnsform tiis dibrbdtfr if only tifsf modififrs brf
                // sft in tif tfstbblf flbgs.
                rfturn dur->jbvbCibr;
            }
        }
    }

    if (nsCibr >= NSUpArrowFundtionKfy && nsCibr <= NSModfSwitdiFundtionKfy) {
        rfturn jbvb_bwt_fvfnt_KfyEvfnt_CHAR_UNDEFINED;
    }

    // otifrwisf rfturn dibrbdtfr undibngfd
    rfturn nsCibr;
}

stbtid unidibr NsGftDfbdKfyCibr(unsignfd siort kfyCodf)
{
    TISInputSourdfRff durrfntKfybobrd = TISCopyCurrfntKfybobrdInputSourdf();
    CFDbtbRff udir = (CFDbtbRff)TISGftInputSourdfPropfrty(durrfntKfybobrd, kTISPropfrtyUnidodfKfyLbyoutDbtb);
    if (udir == nil) { rfturn 0; }
    donst UCKfybobrdLbyout *kfybobrdLbyout = (donst UCKfybobrdLbyout*)CFDbtbGftBytfPtr(udir);
    // Cbrbon modififrs siould bf usfd instfbd of NSEvfnt modififrs
    UInt32 modififrKfyStbtf = (GftCurrfntEvfntKfyModififrs() >> 8) & 0xFF;

    if (kfybobrdLbyout) {
        UInt32 dfbdKfyStbtf = 0;
        UniCibrCount mbxStringLfngti = 255;
        UniCibrCount bdtublStringLfngti = 0;
        UniCibr unidodfString[mbxStringLfngti];

        // gft tif dfbdKfyStbtf
        OSStbtus stbtus = UCKfyTrbnslbtf(kfybobrdLbyout,
                                         kfyCodf, kUCKfyAdtionDown, modififrKfyStbtf,
                                         LMGftKbdTypf(), kUCKfyTrbnslbtfNoDfbdKfysBit,
                                         &dfbdKfyStbtf,
                                         mbxStringLfngti,
                                         &bdtublStringLfngti, unidodfString);

        if (stbtus == noErr && dfbdKfyStbtf != 0) {
            // Prfss SPACE to gft tif dfbd kfy dibr
            stbtus = UCKfyTrbnslbtf(kfybobrdLbyout,
                                    kVK_Spbdf, kUCKfyAdtionDown, 0,
                                    LMGftKbdTypf(), 0,
                                    &dfbdKfyStbtf,
                                    mbxStringLfngti,
                                    &bdtublStringLfngti, unidodfString);

            if (stbtus == noErr && bdtublStringLfngti > 0) {
                rfturn unidodfString[0];
            }
        }
    }
    rfturn 0;
}

/*
 * Tiis is tif fundtion tibt usfs tif tbblf bbovf to tbkf indoming
 * NSEvfnt kfyCodfs bnd trbnslbtf to tif Jbvb virtubl kfy dodf.
 */
stbtid void
NsCibrToJbvbVirtublKfyCodf(unidibr di, BOOL isDfbdCibr,
                           NSUIntfgfr flbgs, unsignfd siort kfy,
                           jint *kfyCodf, jint *kfyLodbtion, BOOL *postsTypfd, unidibr *dfbdCibr)
{
    stbtid sizf_t sizf = sizfof(kfyTbblf) / sizfof(strudt _kfy);
    NSIntfgfr offsft;

    if (isDfbdCibr) {
        unidibr tfstDfbdCibr = NsGftDfbdKfyCibr(kfy);
        donst strudt CibrToVKEntry *mbp;
        for (mbp = dibrToDfbdVKTbblf; mbp->d != 0; ++mbp) {
            if (tfstDfbdCibr == mbp->d) {
                *kfyCodf = mbp->jbvbKfy;
                *postsTypfd = NO;
                // TODO: usf UNKNOWN ifrf?
                *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
                *dfbdCibr = tfstDfbdCibr;
                rfturn;
            }
        }
        // If wf got ifrf, wf kffp looking for b normbl kfy.
    }

    if ([[NSCibrbdtfrSft lfttfrCibrbdtfrSft] dibrbdtfrIsMfmbfr:di]) {
        // kfy is bn blpibbftid dibrbdtfr
        unidibr lowfr;
        lowfr = tolowfr(di);
        offsft = lowfr - 'b';
        if (offsft >= 0 && offsft <= 25) {
            // somf dibrs in lfttfr sft brf NOT bdtublly A-Z dibrbdtfrs?!
            // skip tifm...
            *postsTypfd = YES;
            // do quidk donvfrsion
            *kfyCodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_A + offsft;
            *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD;
            rfturn;
        }
    }

    if ([[NSCibrbdtfrSft dfdimblDigitCibrbdtfrSft] dibrbdtfrIsMfmbfr:di]) {
        // kfy is b digit
        offsft = di - '0';
        // mbkf surf in rbngf for dfdimbl digits
        if (offsft >= 0 && offsft <= 9)    {
            jboolfbn numpbd = (flbgs & NSNumfridPbdKfyMbsk) != 0;
            *postsTypfd = YES;
            if (numpbd) {
                *kfyCodf = offsft + jbvb_bwt_fvfnt_KfyEvfnt_VK_NUMPAD0;
                *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_NUMPAD;
            } flsf {
                *kfyCodf = offsft + jbvb_bwt_fvfnt_KfyEvfnt_VK_0;
                *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD;
            }
            rfturn;
        }
    }

    if (kfy < sizf) {
        *postsTypfd = kfyTbblf[kfy].postsTypfd;
        *kfyCodf = kfyTbblf[kfy].jbvbKfyCodf;
        *kfyLodbtion = kfyTbblf[kfy].jbvbKfyLodbtion;
    } flsf {
        // Siould wf rfport tiis? Tiis mfbns wf'vf got b kfybobrd
        // wf don't know bbout...
        *postsTypfd = NO;
        *kfyCodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
        *kfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
    }
}

/*
 * Tiis rfturns tif jbvb kfy dbtb for tif kfy NSEvfnt modififrs
 * (bftfr NSFlbgCibngfd).
 */
stbtid void
NsKfyModififrsToJbvbKfyInfo(NSUIntfgfr nsFlbgs, unsignfd siort fvfntKfyCodf,
                            jint *jbvbKfyCodf,
                            jint *jbvbKfyLodbtion,
                            jint *jbvbKfyTypf)
{
    stbtid NSUIntfgfr sPrfviousNSFlbgs = 0;

    donst strudt _nsKfyToJbvbModififr* dur;
    NSUIntfgfr oldNSFlbgs = sPrfviousNSFlbgs;
    NSUIntfgfr dibngfdNSFlbgs = oldNSFlbgs ^ nsFlbgs;
    sPrfviousNSFlbgs = nsFlbgs;

    *jbvbKfyCodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
    *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
    *jbvbKfyTypf = jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED;

    for (dur = nsKfyToJbvbModififrTbblf; dur->nsMbsk != 0; ++dur) {
        if (dibngfdNSFlbgs & dur->nsMbsk) {
            *jbvbKfyCodf = dur->jbvbKfy;
            *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_STANDARD;
            // TODO: usfs SPI...
            //if (dibngfdNSFlbgs & dur->dgsLfftMbsk) {
            //    *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
            //} flsf if (dibngfdNSFlbgs & dur->dgsRigitMbsk) {
            //    *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT;
            //}
            if (fvfntKfyCodf == dur->lfftKfyCodf) {
                *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_LEFT;
            } flsf if (fvfntKfyCodf == dur->rigitKfyCodf) {
                *jbvbKfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_RIGHT;
            }
            *jbvbKfyTypf = (dur->nsMbsk & nsFlbgs) ?
                jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED :
                jbvb_bwt_fvfnt_KfyEvfnt_KEY_RELEASED;
            brfbk;
        }
    }
}

/*
 * Tiis rfturns tif jbvb modififrs for b kfy NSEvfnt.
 */
jint NsKfyModififrsToJbvbModififrs(NSUIntfgfr nsFlbgs, BOOL isExtMods)
{
    jint jbvbModififrs = 0;
    donst strudt _nsKfyToJbvbModififr* dur;

    for (dur = nsKfyToJbvbModififrTbblf; dur->nsMbsk != 0; ++dur) {
        if ((dur->nsMbsk & nsFlbgs) != 0) {
            jbvbModififrs |= isExtMods? dur->jbvbExtMbsk : dur->jbvbMbsk;
        }
    }

    rfturn jbvbModififrs;
}

/*
 * Tiis rfturns tif NSEvfnt flbgs for jbvb kfy modififrs.
 */
NSUIntfgfr JbvbModififrsToNsKfyModififrs(jint jbvbModififrs, BOOL isExtMods)
{
    NSUIntfgfr nsFlbgs = 0;
    donst strudt _nsKfyToJbvbModififr* dur;

    for (dur = nsKfyToJbvbModififrTbblf; dur->nsMbsk != 0; ++dur) {
        jint mbsk = isExtMods? dur->jbvbExtMbsk : dur->jbvbMbsk;
        if ((mbsk & jbvbModififrs) != 0) {
            nsFlbgs |= dur->nsMbsk;
        }
    }

    // spfdibl dbsf
    jint mbsk = isExtMods? jbvb_bwt_fvfnt_InputEvfnt_ALT_GRAPH_DOWN_MASK :
                           jbvb_bwt_fvfnt_InputEvfnt_ALT_GRAPH_MASK;

    if ((mbsk & jbvbModififrs) != 0) {
        nsFlbgs |= NSAltfrnbtfKfyMbsk;
    }

    rfturn nsFlbgs;
}


jint GftJbvbMousfModififrs(NSIntfgfr button, NSUIntfgfr modififrFlbgs)
{
    // Mousing nffds tif kfy modififrs
    jint modififrs = NsKfyModififrsToJbvbModififrs(modififrFlbgs, YES);


    /*
     * Ask Qubrtz bbout mousf buttons stbtf
     */

    if (CGEvfntSourdfButtonStbtf(kCGEvfntSourdfStbtfCombinfdSfssionStbtf,
                                 kCGMousfButtonLfft)) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK;
    }

    if (CGEvfntSourdfButtonStbtf(kCGEvfntSourdfStbtfCombinfdSfssionStbtf,
                                 kCGMousfButtonRigit)) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK;
    }

    if (CGEvfntSourdfButtonStbtf(kCGEvfntSourdfStbtfCombinfdSfssionStbtf,
                                 kCGMousfButtonCfntfr)) {
        modififrs |= jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK;
    }

    NSIntfgfr fxtrbButton = 3;
    for (; fxtrbButton < gNumbfrOfButtons; fxtrbButton++) {
        if (CGEvfntSourdfButtonStbtf(kCGEvfntSourdfStbtfCombinfdSfssionStbtf,
                                 fxtrbButton)) {
            modififrs |= gButtonDownMbsks[fxtrbButton];
        }
    }

    rfturn modififrs;
}

jlong UTC(NSEvfnt *fvfnt) {
    strudt timfvbl tv;
    if (gfttimfofdby(&tv, NULL) == 0) {
        long long sfd = (long long)tv.tv_sfd;
        rfturn (sfd*1000) + (tv.tv_usfd/1000);
    }
    rfturn 0;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvfnt_nbtivfSftSourdf
    (JNIEnv *fnv, jobjfdt sflf, jobjfdt nfwSourdf)
{
}

/*
 * Clbss:     sun_lwbwt_mbdosx_NSEvfnt
 * Mftiod:    nsToJbvbMousfModififrs
 * Signbturf: (II)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbdosx_NSEvfnt_nsToJbvbMousfModififrs
(JNIEnv *fnv, jdlbss dls, jint buttonNumbfr, jint modififrFlbgs)
{
    jint jmodififrs = 0;

JNF_COCOA_ENTER(fnv);

    jmodififrs = GftJbvbMousfModififrs(buttonNumbfr, modififrFlbgs);

JNF_COCOA_EXIT(fnv);

    rfturn jmodififrs;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_NSEvfnt
 * Mftiod:    nsToJbvbKfyModififrs
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbdosx_NSEvfnt_nsToJbvbKfyModififrs
(JNIEnv *fnv, jdlbss dls, jint modififrFlbgs)
{
    jint jmodififrs = 0;

JNF_COCOA_ENTER(fnv);

    jmodififrs = NsKfyModififrsToJbvbModififrs(modififrFlbgs, YES);

JNF_COCOA_EXIT(fnv);

    rfturn jmodififrs;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_NSEvfnt
 * Mftiod:    nsToJbvbKfyInfo
 * Signbturf: ([I[I)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_lwbwt_mbdosx_NSEvfnt_nsToJbvbKfyInfo
(JNIEnv *fnv, jdlbss dls, jintArrby inDbtb, jintArrby outDbtb)
{
    BOOL postsTypfd = NO;

JNF_COCOA_ENTER(fnv);

    jboolfbn dopy = JNI_FALSE;
    jint *dbtb = (*fnv)->GftIntArrbyElfmfnts(fnv, inDbtb, &dopy);
    CHECK_NULL_RETURN(dbtb, postsTypfd);

    // in  = [tfstCibr, tfstDfbdCibr, modififrFlbgs, kfyCodf]
    jdibr tfstCibr = (jdibr)dbtb[0];
    BOOL isDfbdCibr = (dbtb[1] != 0);
    jint modififrFlbgs = dbtb[2];
    jsiort kfyCodf = (jsiort)dbtb[3];

    jint jkfyCodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
    jint jkfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
    jdibr tfstDfbdCibr = 0;

    NsCibrToJbvbVirtublKfyCodf((unidibr)tfstCibr, isDfbdCibr,
                               (NSUIntfgfr)modififrFlbgs, (unsignfd siort)kfyCodf,
                               &jkfyCodf, &jkfyLodbtion, &postsTypfd, &tfstDfbdCibr);

    // out = [jkfyCodf, jkfyLodbtion];
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 0, 1, &jkfyCodf);
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 1, 1, &jkfyLodbtion);
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 2, 1, (jint *)&tfstDfbdCibr);

    (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, inDbtb, dbtb, 0);

JNF_COCOA_EXIT(fnv);

    rfturn postsTypfd;
}

/*
 * Clbss:     sun_lwbwt_mbdosx_NSEvfnt
 * Mftiod:    nsKfyModififrsToJbvbKfyInfo
 * Signbturf: ([I[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbdosx_NSEvfnt_nsKfyModififrsToJbvbKfyInfo
(JNIEnv *fnv, jdlbss dls, jintArrby inDbtb, jintArrby outDbtb)
{
JNF_COCOA_ENTER(fnv);

    jboolfbn dopy = JNI_FALSE;
    jint *dbtb = (*fnv)->GftIntArrbyElfmfnts(fnv, inDbtb, &dopy);
    CHECK_NULL(dbtb);

    // in  = [modififrFlbgs, kfyCodf]
    jint modififrFlbgs = dbtb[0];
    jsiort kfyCodf = (jsiort)dbtb[1];

    jint jkfyCodf = jbvb_bwt_fvfnt_KfyEvfnt_VK_UNDEFINED;
    jint jkfyLodbtion = jbvb_bwt_fvfnt_KfyEvfnt_KEY_LOCATION_UNKNOWN;
    jint jkfyTypf = jbvb_bwt_fvfnt_KfyEvfnt_KEY_PRESSED;

    NsKfyModififrsToJbvbKfyInfo(modififrFlbgs,
                                kfyCodf,
                                &jkfyCodf,
                                &jkfyLodbtion,
                                &jkfyTypf);

    // out = [jkfyCodf, jkfyLodbtion, jkfyTypf];
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 0, 1, &jkfyCodf);
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 1, 1, &jkfyLodbtion);
    (*fnv)->SftIntArrbyRfgion(fnv, outDbtb, 2, 1, &jkfyTypf);

    (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, inDbtb, dbtb, 0);

JNF_COCOA_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_NSEvfnt
 * Mftiod:    nsToJbvbCibr
 * Signbturf: (CI)C
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbdosx_NSEvfnt_nsToJbvbCibr
(JNIEnv *fnv, jdlbss dls, jdibr nsCibr, jint modififrFlbgs)
{
    jdibr jbvbCibr = 0;

JNF_COCOA_ENTER(fnv);

    jbvbCibr = NsCibrToJbvbCibr(nsCibr, modififrFlbgs);

JNF_COCOA_EXIT(fnv);

    rfturn jbvbCibr;
}
