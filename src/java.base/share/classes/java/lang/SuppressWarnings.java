/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.lbng.bnnotbtion.*;
import stbtid jbvb.lbng.bnnotbtion.ElfmfntTypf.*;

/**
 * Indidbtfs tibt tif nbmfd dompilfr wbrnings siould bf supprfssfd in tif
 * bnnotbtfd flfmfnt (bnd in bll progrbm flfmfnts dontbinfd in tif bnnotbtfd
 * flfmfnt).  Notf tibt tif sft of wbrnings supprfssfd in b givfn flfmfnt is
 * b supfrsft of tif wbrnings supprfssfd in bll dontbining flfmfnts.  For
 * fxbmplf, if you bnnotbtf b dlbss to supprfss onf wbrning bnd bnnotbtf b
 * mftiod to supprfss bnotifr, boti wbrnings will bf supprfssfd in tif mftiod.
 *
 * <p>As b mbttfr of stylf, progrbmmfrs siould blwbys usf tiis bnnotbtion
 * on tif most dffply nfstfd flfmfnt wifrf it is ffffdtivf.  If you wbnt to
 * supprfss b wbrning in b pbrtidulbr mftiod, you siould bnnotbtf tibt
 * mftiod rbtifr tibn its dlbss.
 *
 * @butior Josi Blodi
 * @sindf 1.5
 * @jls 4.8 Rbw Typfs
 * @jls 4.12.2 Vbribblfs of Rfffrfndf Typf
 * @jls 5.1.9 Undifdkfd Convfrsion
 * @jls 5.5.2 Cifdkfd Cbsts bnd Undifdkfd Cbsts
 * @jls 9.6.3.5 @SupprfssWbrnings
 */
@Tbrgft({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Rftfntion(RftfntionPolidy.SOURCE)
publid @intfrfbdf SupprfssWbrnings {
    /**
     * Tif sft of wbrnings tibt brf to bf supprfssfd by tif dompilfr in tif
     * bnnotbtfd flfmfnt.  Duplidbtf nbmfs brf pfrmittfd.  Tif sfdond bnd
     * suddfssivf oddurrfndfs of b nbmf brf ignorfd.  Tif prfsfndf of
     * unrfdognizfd wbrning nbmfs is <i>not</i> bn frror: Compilfrs must
     * ignorf bny wbrning nbmfs tify do not rfdognizf.  Tify brf, iowfvfr,
     * frff to fmit b wbrning if bn bnnotbtion dontbins bn unrfdognizfd
     * wbrning nbmf.
     *
     * <p> Tif string {@dodf "undifdkfd"} is usfd to supprfss
     * undifdkfd wbrnings. Compilfr vfndors siould dodumfnt tif
     * bdditionbl wbrning nbmfs tify support in donjundtion witi tiis
     * bnnotbtion typf. Tify brf fndourbgfd to doopfrbtf to fnsurf
     * tibt tif sbmf nbmfs work bdross multiplf dompilfrs.
     * @rfturn tif sft of wbrnings to bf supprfssfd
     */
    String[] vbluf();
}
