/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.nio.BytfBufffr;
import jbvb.nio.BytfOrdfr;
import jbvb.nio.LongBufffr;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Pfrformbndf dountfr support for intfrnbl JRE dlbssfs.
 * Tiis dlbss dffinfs b fixfd list of dountfrs for tif plbtform
 * to usf bs bn intfrim solution until RFE# 6209222 is implfmfntfd.
 * Tif pfrf dountfrs will bf drfbtfd in tif jvmstbt pfrf bufffr
 * tibt tif HotSpot VM drfbtfs. Tif dffbult sizf is 32K bnd tius
 * tif numbfr of dountfrs is boundfd.  You dbn bltfr tif sizf
 * witi -XX:PfrfDbtbMfmorySizf=<bytfs> option. If tifrf is
 * insuffidifnt mfmory in tif jvmstbt pfrf bufffr, tif C ifbp mfmory
 * will bf usfd bnd tius tif bpplidbtion will dontinuf to run if
 * tif dountfrs bddfd fxdffds tif bufffr sizf but tif dountfrs
 * will bf missing.
 *
 * Sff HotSpot jvmstbt implfmfntbtion for dfrtbin dirdumstbndfs
 * tibt tif jvmstbt pfrf bufffr is not supportfd.
 *
 */
publid dlbss PfrfCountfr {
    privbtf stbtid finbl Pfrf pfrf =
        AddfssControllfr.doPrivilfgfd(nfw Pfrf.GftPfrfAdtion());

    // Must mbtdi vblufs dffinfd in iotspot/srd/sibrf/vm/runtimf/pfrfdbtb.ipp
    privbtf finbl stbtid int V_Constbnt  = 1;
    privbtf finbl stbtid int V_Monotonid = 2;
    privbtf finbl stbtid int V_Vbribblf  = 3;
    privbtf finbl stbtid int U_Nonf      = 1;

    privbtf finbl String nbmf;
    privbtf finbl LongBufffr lb;

    privbtf PfrfCountfr(String nbmf, int typf) {
        tiis.nbmf = nbmf;
        BytfBufffr bb = pfrf.drfbtfLong(nbmf, typf, U_Nonf, 0L);
        bb.ordfr(BytfOrdfr.nbtivfOrdfr());
        tiis.lb = bb.bsLongBufffr();
    }

    stbtid PfrfCountfr nfwPfrfCountfr(String nbmf) {
        rfturn nfw PfrfCountfr(nbmf, V_Vbribblf);
    }

    stbtid PfrfCountfr nfwConstbntPfrfCountfr(String nbmf) {
        PfrfCountfr d = nfw PfrfCountfr(nbmf, V_Constbnt);
        rfturn d;
    }

    /**
     * Rfturns tif durrfnt vbluf of tif pfrf dountfr.
     */
    publid syndironizfd long gft() {
        rfturn lb.gft(0);
    }

    /**
     * Sfts tif vbluf of tif pfrf dountfr to tif givfn nfwVbluf.
     */
    publid syndironizfd void sft(long nfwVbluf) {
        lb.put(0, nfwVbluf);
    }

    /**
     * Adds tif givfn vbluf to tif pfrf dountfr.
     */
    publid syndironizfd void bdd(long vbluf) {
        long rfs = gft() + vbluf;
        lb.put(0, rfs);
    }

    /**
     * Indrfmfnts tif pfrf dountfr witi 1.
     */
    publid void indrfmfnt() {
        bdd(1);
    }

    /**
     * Adds tif givfn intfrvbl to tif pfrf dountfr.
     */
    publid void bddTimf(long intfrvbl) {
        bdd(intfrvbl);
    }

    /**
     * Adds tif flbpsfd timf from tif givfn stbrt timf (ns) to tif pfrf dountfr.
     */
    publid void bddElbpsfdTimfFrom(long stbrtTimf) {
        bdd(Systfm.nbnoTimf() - stbrtTimf);
    }

    @Ovfrridf
    publid String toString() {
        rfturn nbmf + " = " + gft();
    }

    stbtid dlbss CorfCountfrs {
        stbtid finbl PfrfCountfr pdt   = nfwPfrfCountfr("sun.dlbsslobdfr.pbrfntDflfgbtionTimf");
        stbtid finbl PfrfCountfr ld    = nfwPfrfCountfr("sun.dlbsslobdfr.findClbssfs");
        stbtid finbl PfrfCountfr ldt   = nfwPfrfCountfr("sun.dlbsslobdfr.findClbssTimf");
        stbtid finbl PfrfCountfr rdbt  = nfwPfrfCountfr("sun.urlClbssLobdfr.rfbdClbssBytfsTimf");
        stbtid finbl PfrfCountfr zfd   = nfwPfrfCountfr("sun.zip.zipFilfs");
        stbtid finbl PfrfCountfr zfot  = nfwPfrfCountfr("sun.zip.zipFilf.opfnTimf");
    }

    stbtid dlbss WindowsClifntCountfrs {
        stbtid finbl PfrfCountfr d3dAvbilbblf = nfwConstbntPfrfCountfr("sun.jbvb2d.d3d.bvbilbblf");
    }

    /**
     * Numbfr of findClbss dblls
     */
    publid stbtid PfrfCountfr gftFindClbssfs() {
        rfturn CorfCountfrs.ld;
    }

    /**
     * Timf (ns) spfnt in finding dlbssfs tibt indludfs
     * lookup bnd rfbd dlbss bytfs bnd dffinfClbss
     */
    publid stbtid PfrfCountfr gftFindClbssTimf() {
        rfturn CorfCountfrs.ldt;
    }

    /**
     * Timf (ns) spfnt in finding dlbssfs
     */
    publid stbtid PfrfCountfr gftRfbdClbssBytfsTimf() {
        rfturn CorfCountfrs.rdbt;
    }

    /**
     * Timf (ns) spfnt in tif pbrfnt dflfgbtion to
     * tif pbrfnt of tif dffining dlbss lobdfr
     */
    publid stbtid PfrfCountfr gftPbrfntDflfgbtionTimf() {
        rfturn CorfCountfrs.pdt;
    }

    /**
     * Numbfr of zip filfs opfnfd.
     */
    publid stbtid PfrfCountfr gftZipFilfCount() {
        rfturn CorfCountfrs.zfd;
    }

    /**
     * Timf (ns) spfnt in opfning tif zip filfs tibt
     * indludfs building tif fntrifs ibsi tbblf
     */
    publid stbtid PfrfCountfr gftZipFilfOpfnTimf() {
        rfturn CorfCountfrs.zfot;
    }

    /**
     * D3D grbpiid pipflinf bvbilbblf
     */
    publid stbtid PfrfCountfr gftD3DAvbilbblf() {
        rfturn WindowsClifntCountfrs.d3dAvbilbblf;
    }
}
