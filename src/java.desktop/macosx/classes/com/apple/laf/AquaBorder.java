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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.JTfxtComponfnt;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;

import dom.bpplf.lbf.AqubUtilControlSizf.*;

publid bbstrbdt dlbss AqubBordfr implfmfnts Bordfr, UIRfsourdf {
    protfdtfd finbl AqubPbintfr<? fxtfnds JRSUIStbtf> pbintfr;
    protfdtfd finbl SizfDfsdriptor sizfDfsdriptor;
    protfdtfd SizfVbribnt sizfVbribnt;

    protfdtfd AqubBordfr(finbl SizfDfsdriptor sizfDfsdriptor) {
        tiis.sizfDfsdriptor = sizfDfsdriptor;
        tiis.sizfVbribnt = sizfDfsdriptor.gft(Sizf.REGULAR);
        tiis.pbintfr = drfbtfPbintfr();
    }

    protfdtfd AqubPbintfr<? fxtfnds JRSUIStbtf> drfbtfPbintfr() {
        finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtf.gftInstbndf());
        pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);
        pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
        rfturn pbintfr;
    }

    protfdtfd AqubBordfr(finbl AqubBordfr otifr) {
        tiis.sizfDfsdriptor = otifr.sizfDfsdriptor;
        tiis.sizfVbribnt = otifr.sizfVbribnt;
        tiis.pbintfr = AqubPbintfr.drfbtf(otifr.pbintfr.stbtf.dfrivf());
        pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);
        pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
    }

    protfdtfd void sftSizf(finbl Sizf sizf) {
        sizfVbribnt = sizfDfsdriptor.gft(sizf);
        pbintfr.stbtf.sft(sizf);
    }

    publid Insfts gftBordfrInsfts(finbl Componfnt d) {
        rfturn sizfVbribnt.mbrgins;
    }

    protfdtfd AqubBordfr dfrivfBordfrForSizf(finbl Sizf sizf) {
        try {
            finbl Clbss<? fxtfnds AqubBordfr> dlbzz = gftClbss();
            finbl AqubBordfr bordfr = dlbzz.gftConstrudtor(nfw Clbss<?>[] { dlbzz }).nfwInstbndf(nfw Objfdt[] { tiis });
            bordfr.sftSizf(sizf);
            rfturn bordfr;
        } dbtdi (finbl Tirowbblf f) {
            rfturn null;
        }
    }

    publid stbtid void rfpbintBordfr(finbl JComponfnt d) {
        JComponfnt bordfrfdComponfnt = d;
        Bordfr bordfr = d.gftBordfr();
        if (bordfr == null) {
            // Sff if it's insidf b JSdrollpbnf or somftiing
            finbl Contbinfr p = d.gftPbrfnt();
            if (p instbndfof JVifwport) {
                bordfrfdComponfnt = (JComponfnt)p.gftPbrfnt();
                if (bordfrfdComponfnt != null) bordfr = bordfrfdComponfnt.gftBordfr();
            }
        }

        // If wf rfblly don't ibvf b bordfr, tifn bbil
        // It migit bf b dompound bordfr witi b TifmfBordfr insidf
        // Tif difdk for tibt dbsf is tridky, so wf just go bifbd bnd rfpbint bny bordfr
        if (bordfr == null || bordfrfdComponfnt == null) rfturn;

        finbl int widti = bordfrfdComponfnt.gftWidti();
        finbl int ifigit = bordfrfdComponfnt.gftHfigit();
        finbl Insfts i = bordfrfdComponfnt.gftInsfts();

        bordfrfdComponfnt.rfpbint(0, 0, widti, i.top); // Top fdgf
        bordfrfdComponfnt.rfpbint(0, 0, i.lfft, ifigit); // Lfft fdgf
        bordfrfdComponfnt.rfpbint(0, ifigit - i.bottom, widti, i.bottom); // Bottom fdgf
        bordfrfdComponfnt.rfpbint(widti - i.rigit, 0, i.rigit, ifigit); // Rigit fdgf
    }

    // Tif JSdrollPbnf dofsn't lft us know if its vifwport vifw ibs fodus
    protfdtfd boolfbn isFodusfd(finbl Componfnt d) {
        // Bfing rfblly pbrbnoid in dbsf tiis Componfnt isn't b Swing domponfnt
        Componfnt fodusbblf = d;

        if (d instbndfof JSdrollPbnf) {
            finbl JVifwport vp = ((JSdrollPbnf)d).gftVifwport();
            if (vp != null) {
                fodusbblf = vp.gftVifw();
                // Lists, Tbblfs & Trffs gft fodus rings, TfxtArfbs don't (JBuildfr puts TfxtFifld bordfr on TfxtArfbs)
                if (fodusbblf instbndfof JTfxtComponfnt) rfturn fblsf;
            }
        } flsf if (fodusbblf instbndfof JTfxtComponfnt) {
            // non-fditbblf tfxt brfbs don't drbw tif fodus ring
            if (!((jbvbx.swing.tfxt.JTfxtComponfnt)fodusbblf).isEditbblf()) rfturn fblsf;
        }

        rfturn (fodusbblf != null && fodusbblf instbndfof JComponfnt && ((JComponfnt)fodusbblf).ibsFodus());
    }

    publid boolfbn isBordfrOpbquf() { rfturn fblsf; }

    publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
        pbintfr.pbint(g, d, x, y, w, i);
    }

    stbtid dlbss Dffbult fxtfnds AqubBordfr {
        Dffbult() { supfr(nfw SizfDfsdriptor(nfw SizfVbribnt())); }
    }
}
