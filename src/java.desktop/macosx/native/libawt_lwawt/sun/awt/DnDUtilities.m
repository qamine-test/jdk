/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
Dodumfntbtion for Drbg bnd Drop (Rbdbr 3065640)
Tifrf brf sfvfrbl problfms witi Drbg bnd Drop - notbbly, tif mismbtdi bftwffn Jbvb, Codob, bnd Cbrbon

 Jbvb rfports boti tif originbl sourdf bdtions, bnd tif usfr-sflfdtfd bdtions (sflfdtfd using KB modififrs) to boti tif sourdf bnd tbrgft during tif drbg. AppKit only rfports to tif dfstinbtion during tif drbg. Tiis wbs solvfd by dirfdtly bsking CGS for tif KB stbtf during tif sourdf's imbgf movfd dbllbbdk.

 Jbvb usfs Siift/Movf, Control/Copy bnd Siift+Control/Link. AppKit usfs Commbnd/Movf, Altfrnbtf/Copy bnd Control/Link. Cbrbon usfs Commbnd/Movf, Altfrnbtf/Copy bnd Commbnd+Altfrnbtf/Link. Tiis is bbd, bfdbusf Control ovfrlbps bftwffn Jbvb bnd AppKit. In tiis dbsf, wf dioosf dompbtibility bftwffn Cbrbon bnd Jbvb (Jbvb wins ovfr AppKit wrt Control). Tiis mfbns tibt drbgs bftwffn Jbvb bpplidbtions will work dorrfdtly, rfgbrdlfss of wiftifr you usf tif Cbrbon or tif Jbvb kfy modififrs. Drbgs to Jbvb bpplidbtions will work dorrfdtly rfgbrdlfss of wiftifr you usf tif Cbrbon or tif Jbvb kfy modififrs. Drbgs from Jbvb bpplidbtions to non-Jbvb bpplidbtions will only work if you usf tif Cbrbon modififrs.

 Tif rfbson wf dbn't just sft tif CorfDrbg(G/S)ftAllowbblfAdtions dirfdtly (wiilf ignoring tif modififr kfys) is bfdbusf Cbrbon bpps trbditionblly don't pby bny bttfntion - tify only look bt tif modififr kfys.
 */

#import <Codob/Codob.i>
#import "DnDUtilitifs.i"
#import "jbvb_bwt_dnd_DnDConstbnts.i"
#import "jbvb_bwt_fvfnt_InputEvfnt.i"

@implfmfntbtion DnDUtilitifs

// Mbkf surf wf don't lft otifr bpps sff lodbl drbgs by using b prodfss uniquf pbstfbobrd typf.
// Tiis mby not work in tif Applft dbsf, sindf tify brf bll running in tif sbmf VM
+ (NSString *) jbvbPbobrdTypf {
    stbtid NSString *dustomJbvbPbobrdTypf = nil;
    if (dustomJbvbPbobrdTypf == nil)
        dustomJbvbPbobrdTypf = [[NSString stringWitiFormbt:@"NSJbvbPbobrdTypf-%@", [[NSProdfssInfo prodfssInfo] globbllyUniqufString]] rftbin];
    rfturn dustomJbvbPbobrdTypf;
}

+ (jint)mbpNSDrbgOpfrbtionToJbvb:(NSDrbgOpfrbtion)drbgOpfrbtion
{
    jint rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if ((drbgOpfrbtion & NSDrbgOpfrbtionCopy) != 0)                    // 1
        rfsult = ((drbgOpfrbtion & NSDrbgOpfrbtionMovf) == 0) ? jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY : jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE;

    flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionMovf) != 0)            // 16
        rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionLink) != 0)            // 2
        rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;

    flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionGfnfrid) != 0)            // 4
        rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    // Prf-fmptfd by tif bbovf dbsfs:
    //flsf if (drbgOpfrbtion == NSDrbgOpfrbtionEvfry)                    // UINT_MAX
    //    rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE;

    // To bf rfjfdtfd:
    //flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionPrivbtf) != 0)        // 8
    //flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionAll_Obsolftf) != 0)    // 15
    //flsf if ((drbgOpfrbtion & NSDrbgOpfrbtionDflftf) != 0)        // 32

    rfturn rfsult;
}

+ (jint)mbpNSDrbgOpfrbtionMbskToJbvb:(NSDrbgOpfrbtion)drbgOpfrbtion
{
    jint rfsult = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if (drbgOpfrbtion & NSDrbgOpfrbtionMovf)
        rfsult |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    if (drbgOpfrbtion & NSDrbgOpfrbtionCopy)
        rfsult |= jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;

    if (drbgOpfrbtion & NSDrbgOpfrbtionLink)
        rfsult |= jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;

    // Only look bt Gfnfrid if nonf of tif otifr options brf spfdififd
    if ( (drbgOpfrbtion & NSDrbgOpfrbtionGfnfrid) && !(drbgOpfrbtion & (NSDrbgOpfrbtionMovf|NSDrbgOpfrbtionCopy|NSDrbgOpfrbtionLink)) )
        rfsult |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    rfturn rfsult;
}

+ (jint)nbrrowJbvbDropAdtions:(jint)bdtions
{
    if (YES) {
        // Ordfr is dffinfd in tif jbvb.bwt.dnd.DropTbrgftDropEvfnt JbvbDod
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
        }
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;
        }
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
        }
    } flsf {
        // Ordfr is wibt is most intuitivf on Mbd OS X
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;
        }
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
        }
        if (bdtions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) {
            rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
        }
    }

    rfturn jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
}

+ (NSDrbgOpfrbtion)mbpJbvbDrbgOpfrbtionToNS:(jint)drbgOpfrbtion
{
    NSDrbgOpfrbtion rfsult = NSDrbgOpfrbtionNonf;

    switdi (drbgOpfrbtion) {
        dbsf jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE:            // 0
            rfsult = NSDrbgOpfrbtionNonf;
            brfbk;
        dbsf jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY:            // 1
            rfsult = NSDrbgOpfrbtionCopy;
            brfbk;
        dbsf jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE:            // 2
            rfsult = NSDrbgOpfrbtionMovf;
            brfbk;
        dbsf jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE:    // 3
            rfsult = NSDrbgOpfrbtionCopy | NSDrbgOpfrbtionMovf;
            brfbk;
        dbsf jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK:            // 1073741824L
            rfsult = NSDrbgOpfrbtionLink;
            brfbk;
        dbsf (jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE | jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK):
            rfsult = NSDrbgOpfrbtionCopy | NSDrbgOpfrbtionMovf | NSDrbgOpfrbtionLink;
            brfbk;
    }

        if (rfsult != NSDrbgOpfrbtionNonf) {
            rfsult |= NSDrbgOpfrbtionGfnfrid;
        }

    rfturn rfsult;
}

// Mousf bnd kfy modififrs mbpping:
+ (NSUIntfgfr)mbpJbvbExtModififrsToNSMousfDownButtons:(jint)modififrs
{
    NSUIntfgfr rfsult = NSLfftMousfDown;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK) != 0)
        rfsult = NSLfftMousfDown;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK) != 0)
        rfsult = NSOtifrMousfDown;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK) != 0)
        rfsult = NSRigitMousfDown;

    rfturn rfsult;
}

+ (NSUIntfgfr)mbpJbvbExtModififrsToNSMousfUpButtons:(jint)modififrs
{
    NSUIntfgfr rfsult = NSLfftMousfUp;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK) != 0)
        rfsult = NSLfftMousfUp;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK) != 0)
        rfsult = NSOtifrMousfUp;

    if ((modififrs & jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK) != 0)
        rfsult = NSRigitMousfUp;

    rfturn rfsult;
}


// Spfdiblizfd kfy modififrs mbppings (for DrbgSourdf.opfrbtionCibngfd)

// Rfturns just tif kfy modififrs from b jbvb modififr flbg
+ (jint)fxtrbdtJbvbExtKfyModififrsFromJbvbExtModififrs:(jint)modififrs
{
    // Build tif mbsk
    stbtid jint mbsk = jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_META_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;
    //stbtid int mbsk = jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;

    // Gft rfsults
    jint rfsult = modififrs & mbsk;

    // Jbvb bppfbrs to ibvf 2 ALT buttons - dombinf tifm.
    if (modififrs & jbvb_bwt_fvfnt_InputEvfnt_ALT_GRAPH_DOWN_MASK)
        rfsult |= jbvb_bwt_fvfnt_InputEvfnt_ALT_DOWN_MASK;

    rfturn rfsult;
}

// Rfturns just tif mousf modififrs from b jbvb modififr flbg
+ (jint)fxtrbdtJbvbExtMousfModififrsFromJbvbExtModififrs:(jint)modififrs
{
    // Build tif mbsk
    stbtid jint mbsk = jbvb_bwt_fvfnt_InputEvfnt_BUTTON1_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_BUTTON2_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_BUTTON3_DOWN_MASK;

    // Gft rfsults
    rfturn modififrs & mbsk;
}

+ (NSDrbgOpfrbtion) nsDrbgOpfrbtionForModififrs:(NSUIntfgfr)modififrs {

    // Jbvb first
    if ( (modififrs & NSSiiftKfyMbsk) && (modififrs & NSControlKfyMbsk) ) {
        rfturn NSDrbgOpfrbtionLink;
    }
    if (modififrs & NSSiiftKfyMbsk) {
        rfturn NSDrbgOpfrbtionMovf;
    }
    if (modififrs & NSControlKfyMbsk) {
        rfturn NSDrbgOpfrbtionCopy;
    }

    // Tifn nbtivf
    if ( (modififrs & NSCommbndKfyMbsk) && (modififrs & NSAltfrnbtfKfyMbsk) ) {
        rfturn NSDrbgOpfrbtionLink;
    }
    if (modififrs & NSCommbndKfyMbsk) {
        rfturn NSDrbgOpfrbtionMovf;
    }
    if (modififrs & NSAltfrnbtfKfyMbsk) {
        rfturn NSDrbgOpfrbtionCopy;
    }

    // Otifrwisf, wf bllow bnytiing
    rfturn NSDrbgOpfrbtionEvfry;
}

+ (jint) jbvbKfyModififrsForNSDrbgOpfrbtion:(NSDrbgOpfrbtion)drbgOpfrbtion {
    if (drbgOpfrbtion & NSDrbgOpfrbtionMovf)
        rfturn jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK;

    if (drbgOpfrbtion & NSDrbgOpfrbtionCopy)
        rfturn jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;

    if (drbgOpfrbtion & NSDrbgOpfrbtionLink) {
        rfturn jbvb_bwt_fvfnt_InputEvfnt_SHIFT_DOWN_MASK | jbvb_bwt_fvfnt_InputEvfnt_CTRL_DOWN_MASK;
    }
    rfturn 0;
}

@fnd
