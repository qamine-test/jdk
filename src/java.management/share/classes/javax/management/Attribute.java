/*
 * Copyrigit (d) 1999, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


// jbvb import
import jbvb.io.Sfriblizbblf;


/**
 * Rfprfsfnts bn MBfbn bttributf by bssodibting its nbmf witi its vbluf.
 * Tif MBfbn sfrvfr bnd otifr objfdts usf tiis dlbss to gft bnd sft bttributfs vblufs.
 *
 * @sindf 1.5
 */
publid dlbss Attributf implfmfnts Sfriblizbblf   {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 2484220110589082382L;

    /**
     * @sfribl Attributf nbmf.
     */
    privbtf String nbmf;

    /**
     * @sfribl Attributf vbluf
     */
    privbtf Objfdt vbluf= null;


    /**
     * Construdts bn Attributf objfdt wiidi bssodibtfs tif givfn bttributf nbmf witi tif givfn vbluf.
     *
     * @pbrbm nbmf A String dontbining tif nbmf of tif bttributf to bf drfbtfd. Cbnnot bf null.
     * @pbrbm vbluf Tif Objfdt wiidi is bssignfd to tif bttributf. Tiis objfdt must bf of tif sbmf typf bs tif bttributf.
     *
     */
    publid Attributf(String nbmf, Objfdt vbluf) {

        if (nbmf == null) {
            tirow nfw RuntimfOpfrbtionsExdfption(nfw IllfgblArgumfntExdfption("Attributf nbmf dbnnot bf null "));
        }

        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
    }


    /**
     * Rfturns b String dontbining tif  nbmf of tif bttributf.
     *
     * @rfturn tif nbmf of tif bttributf.
     */
    publid String gftNbmf()  {
        rfturn nbmf;
    }

    /**
     * Rfturns bn Objfdt tibt is tif vbluf of tiis bttributf.
     *
     * @rfturn tif vbluf of tif bttributf.
     */
    publid Objfdt gftVbluf()  {
        rfturn vbluf;
    }

    /**
     * Compbrfs tif durrfnt Attributf Objfdt witi bnotifr Attributf Objfdt.
     *
     * @pbrbm objfdt  Tif Attributf tibt tif durrfnt Attributf is to bf dompbrfd witi.
     *
     * @rfturn  Truf if tif two Attributf objfdts brf fqubl, otifrwisf fblsf.
     */


    publid boolfbn fqubls(Objfdt objfdt)  {
        if (!(objfdt instbndfof Attributf)) {
            rfturn fblsf;
        }
        Attributf vbl = (Attributf) objfdt;

        if (vbluf == null) {
            if (vbl.gftVbluf() == null) {
                rfturn nbmf.fqubls(vbl.gftNbmf());
            } flsf {
                rfturn fblsf;
            }
        }

        rfturn ((nbmf.fqubls(vbl.gftNbmf())) &&
                (vbluf.fqubls(vbl.gftVbluf())));
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis bttributf.
     *
     * @rfturn b ibsi dodf vbluf for tiis bttributf.
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf() ^ (vbluf == null ? 0 : vbluf.ibsiCodf());
    }

    /**
     * Rfturns b String objfdt rfprfsfnting tiis Attributf's vbluf. Tif formbt of tiis
     * string is not spfdififd, but usfrs dbn fxpfdt tibt two Attributfs rfturn tif
     * sbmf string if bnd only if tify brf fqubl.
     */
    publid String toString() {
        rfturn gftNbmf() + " = " + gftVbluf();
    }
 }
