/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtStrfbmExdfption;

/**
 * Tif <dodf>TrbnsformAttributf</dodf> dlbss providfs bn immutbblf
 * wrbppfr for b trbnsform so tibt it is sbff to usf bs bn bttributf.
 */
publid finbl dlbss TrbnsformAttributf implfmfnts Sfriblizbblf {

    /**
     * Tif <dodf>AffinfTrbnsform</dodf> for tiis
     * <dodf>TrbnsformAttributf</dodf>, or <dodf>null</dodf>
     * if <dodf>AffinfTrbnsform</dodf> is tif idfntity trbnsform.
     */
    privbtf AffinfTrbnsform trbnsform;

    /**
     * Wrbps tif spfdififd trbnsform.  Tif trbnsform is dlonfd bnd b
     * rfffrfndf to tif dlonf is kfpt.  Tif originbl trbnsform is undibngfd.
     * If null is pbssfd bs tif brgumfnt, tiis donstrudtor bfibvfs bs tiougi
     * it wfrf tif idfntity trbnsform.  (Notf tibt it is prfffrbblf to usf
     * {@link #IDENTITY} in tiis dbsf.)
     * @pbrbm trbnsform tif spfdififd {@link AffinfTrbnsform} to bf wrbppfd,
     * or null.
     */
    publid TrbnsformAttributf(AffinfTrbnsform trbnsform) {
        if (trbnsform != null && !trbnsform.isIdfntity()) {
            tiis.trbnsform = nfw AffinfTrbnsform(trbnsform);
        }
    }

    /**
     * Rfturns b dopy of tif wrbppfd trbnsform.
     * @rfturn b <dodf>AffinfTrbnsform</dodf> tibt is b dopy of tif wrbppfd
     * trbnsform of tiis <dodf>TrbnsformAttributf</dodf>.
     */
    publid AffinfTrbnsform gftTrbnsform() {
        AffinfTrbnsform bt = trbnsform;
        rfturn (bt == null) ? nfw AffinfTrbnsform() : nfw AffinfTrbnsform(bt);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif wrbppfd trbnsform is
     * bn idfntity trbnsform.
     * @rfturn <dodf>truf</dodf> if tif wrbppfd trbnsform is
     * bn idfntity trbnsform; <dodf>fblsf</dodf> otifrwisf.
     * @sindf 1.4
     */
    publid boolfbn isIdfntity() {
        rfturn trbnsform == null;
    }

    /**
     * A <dodf>TrbnsformAttributf</dodf> rfprfsfnting tif idfntity trbnsform.
     * @sindf 1.6
     */
    publid stbtid finbl TrbnsformAttributf IDENTITY = nfw TrbnsformAttributf(null);

    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
      tirows jbvb.lbng.ClbssNotFoundExdfption,
             jbvb.io.IOExdfption
    {
        // sigi -- 1.3 fxpfdts trbnsform is nfvfr null, so wf nffd to blwbys writf onf out
        if (tiis.trbnsform == null) {
            tiis.trbnsform = nfw AffinfTrbnsform();
        }
        s.dffbultWritfObjfdt();
    }

    /*
     * @sindf 1.6
     */
    privbtf Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption {
        if (trbnsform == null || trbnsform.isIdfntity()) {
            rfturn IDENTITY;
        }
        rfturn tiis;
    }

    // Addfd for sfribl bbdkwbrds dompbtibility (4348425)
    stbtid finbl long sfriblVfrsionUID = 3356247357827709530L;

    /**
     * @sindf 1.6
     */
    publid int ibsiCodf() {
        rfturn trbnsform == null ? 0 : trbnsform.ibsiCodf();
    }

    /**
     * Rfturns <dodf>truf</dodf> if ris is b <dodf>TrbnsformAttributf</dodf>
     * wiosf trbnsform is fqubl to tiis <dodf>TrbnsformAttributf</dodf>'s
     * trbnsform.
     * @pbrbm ris tif objfdt to dompbrf to
     * @rfturn <dodf>truf</dodf> if tif brgumfnt is b <dodf>TrbnsformAttributf</dodf>
     * wiosf trbnsform is fqubl to tiis <dodf>TrbnsformAttributf</dodf>'s
     * trbnsform.
     * @sindf 1.6
     */
    publid boolfbn fqubls(Objfdt ris) {
        if (ris != null) {
            try {
                TrbnsformAttributf tibt = (TrbnsformAttributf)ris;
                if (trbnsform == null) {
                    rfturn tibt.trbnsform == null;
                }
                rfturn trbnsform.fqubls(tibt.trbnsform);
            }
            dbtdi (ClbssCbstExdfption f) {
            }
        }
        rfturn fblsf;
    }
}
