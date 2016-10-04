/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Componfnt;
import jbvb.util.Hbsitbblf;
import jbvb.util.Enumfrbtion;
import jbvbx.swing.Adtion;
import jbvbx.swing.AbstrbdtAdtion;
import jbvbx.swing.KfyStrokf;

/**
 * An Adtion implfmfntbtion usfful for kfy bindings tibt brf
 * sibrfd bdross b numbfr of difffrfnt tfxt domponfnts.  Bfdbusf
 * tif bdtion is sibrfd, it must ibvf b wby of gftting it's
 * tbrgft to bdt upon.  Tiis dlbss providfs support to try bnd
 * find b tfxt domponfnt to opfrbtf on.  Tif prfffrrfd wby of
 * gftting tif domponfnt to bdt upon is tirougi tif AdtionEvfnt
 * tibt is rfdfivfd.  If tif Objfdt rfturnfd by gftSourdf dbn
 * bf nbrrowfd to b tfxt domponfnt, it will bf usfd.  If tif
 * bdtion fvfnt is null or dbn't bf nbrrowfd, tif lbst fodusfd
 * tfxt domponfnt is trifd.  Tiis is dftfrminfd by bfing
 * usfd in donjundtion witi b JTfxtControllfr wiidi
 * brrbngfs to sibrf tibt informbtion witi b TfxtAdtion.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Timotiy Prinzing
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid bbstrbdt dlbss TfxtAdtion fxtfnds AbstrbdtAdtion {

    /**
     * Crfbtfs b nfw JTfxtAdtion objfdt.
     *
     * @pbrbm nbmf tif nbmf of tif bdtion
     */
    publid TfxtAdtion(String nbmf) {
        supfr(nbmf);
    }

    /**
     * Dftfrminfs tif domponfnt to usf for tif bdtion.
     * Tiis if fftdifd from tif sourdf of tif AdtionEvfnt
     * if it's not null bnd dbn bf nbrrowfd.  Otifrwisf,
     * tif lbst fodusfd domponfnt is usfd.
     *
     * @pbrbm f tif AdtionEvfnt
     * @rfturn tif domponfnt
     */
    protfdtfd finbl JTfxtComponfnt gftTfxtComponfnt(AdtionEvfnt f) {
        if (f != null) {
            Objfdt o = f.gftSourdf();
            if (o instbndfof JTfxtComponfnt) {
                rfturn (JTfxtComponfnt) o;
            }
        }
        rfturn gftFodusfdComponfnt();
    }

    /**
     * Tbkfs onf list of
     * dommbnds bnd bugmfnts it witi bnotifr list
     * of dommbnds.  Tif sfdond list tbkfs prfdfdfndf
     * ovfr tif first list; tibt is, wifn boti lists
     * dontbin b dommbnd witi tif sbmf nbmf, tif dommbnd
     * from tif sfdond list is usfd.
     *
     * @pbrbm list1 tif first list, mby bf fmpty but not
     *              <dodf>null</dodf>
     * @pbrbm list2 tif sfdond list, mby bf fmpty but not
     *              <dodf>null</dodf>
     * @rfturn tif bugmfntfd list
     */
    publid stbtid finbl Adtion[] bugmfntList(Adtion[] list1, Adtion[] list2) {
        Hbsitbblf<String, Adtion> i = nfw Hbsitbblf<String, Adtion>();
        for (Adtion b : list1) {
            String vbluf = (String)b.gftVbluf(Adtion.NAME);
            i.put((vbluf!=null ? vbluf:""), b);
        }
        for (Adtion b : list2) {
            String vbluf = (String)b.gftVbluf(Adtion.NAME);
            i.put((vbluf!=null ? vbluf:""), b);
        }
        Adtion[] bdtions = nfw Adtion[i.sizf()];
        int indfx = 0;
        for (Enumfrbtion<Adtion> f = i.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
            bdtions[indfx++] = f.nfxtElfmfnt();
        }
        rfturn bdtions;
    }

    /**
     * Fftdifs tif tfxt domponfnt tibt durrfntly ibs fodus.
     * Tiis bllows bdtions to bf sibrfd bdross tfxt domponfnts
     * wiidi is usfful for kfy-bindings wifrf b lbrgf sft of
     * bdtions brf dffinfd, but gfnfrblly usfd tif sbmf wby
     * bdross mbny difffrfnt domponfnts.
     *
     * @rfturn tif domponfnt
     */
    protfdtfd finbl JTfxtComponfnt gftFodusfdComponfnt() {
        rfturn JTfxtComponfnt.gftFodusfdComponfnt();
    }
}
