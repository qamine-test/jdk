/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rmid.nfwrmid;

import dom.sun.jbvbdod.ClbssDod;
import dom.sun.jbvbdod.RootDod;
import jbvb.io.Filf;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;

import stbtid sun.rmi.rmid.nfwrmid.Constbnts.*;

/**
 * Tif fnvironmfnt for bn rmid dompilbtion bbtdi.
 *
 * A BbtdiEnvironmfnt dontbins b RootDod, wiidi is tif fntry point
 * into tif dodlft fnvironmfnt for tif bssodibtfd rmid dompilbtion
 * bbtdi.  A BbtdiEnvironmfnt dollfdts tif sourdf filfs gfnfrbtfd
 * during tif bbtdi's fxfdution, for fvfntubl sourdf dodf dompilbtion
 * bnd, possibly, dflftion.  Errors tibt oddur during gfnfrbtion
 * bdtivity siould bf rfportfd tirougi tif BbtdiEnvironmfnt's "frror"
 * mftiod.
 *
 * A protodol-spfdifid gfnfrbtor dlbss mby rfquirf tif usf of b
 * pbrtidulbr BbtdiEnvironmfnt subdlbss for fnibndfd fnvironmfnt
 * fundtionblity.  A BbtdiEnvironmfnt subdlbss must dfdlbrf b
 * publid donstrudtor witi onf pbrbmftfr of typf RootDod.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 *
 * @butior Pftfr Jonfs
 **/
publid dlbss BbtdiEnvironmfnt {

    privbtf finbl RootDod rootDod;

    /** dbdifd ClbssDod for dfrtbin typfs usfd by rmid */
    privbtf finbl ClbssDod dodRfmotf;
    privbtf finbl ClbssDod dodExdfption;
    privbtf finbl ClbssDod dodRfmotfExdfption;
    privbtf finbl ClbssDod dodRuntimfExdfption;

    privbtf boolfbn vfrbosf = fblsf;
    privbtf finbl List<Filf> gfnfrbtfdFilfs = nfw ArrbyList<Filf>();

    /**
     * Crfbtfs b nfw BbtdiEnvironmfnt witi tif spfdififd RootDod.
     **/
    publid BbtdiEnvironmfnt(RootDod rootDod) {
        tiis.rootDod = rootDod;

        /*
         * Initiblizf dbdifd ClbssDod for typfs usfd by rmid.  Notf
         * tibt bny of tifsf dould bf null if tif boot dlbss pbti is
         * indorrfdt, wiidi dould dbusf b NullPointfrExdfption lbtfr.
         */
        dodRfmotf = rootDod().dlbssNbmfd(REMOTE);
        dodExdfption = rootDod().dlbssNbmfd(EXCEPTION);
        dodRfmotfExdfption = rootDod().dlbssNbmfd(REMOTE_EXCEPTION);
        dodRuntimfExdfption = rootDod().dlbssNbmfd(RUNTIME_EXCEPTION);
    }

    /**
     * Rfturns tif RootDod for tiis fnvironmfnt.
     **/
    publid RootDod rootDod() {
        rfturn rootDod;
    }

    publid ClbssDod dodRfmotf() { rfturn dodRfmotf; }
    publid ClbssDod dodExdfption() { rfturn dodExdfption; }
    publid ClbssDod dodRfmotfExdfption() { rfturn dodRfmotfExdfption; }
    publid ClbssDod dodRuntimfExdfption() { rfturn dodRuntimfExdfption; }

    /**
     * Sfts tiis fnvironmfnt's vfrbosity stbtus.
     **/
    publid void sftVfrbosf(boolfbn vfrbosf) {
        tiis.vfrbosf = vfrbosf;
    }

    /**
     * Rfturns tiis fnvironmfnt's vfrbosity stbtus.
     **/
    publid boolfbn vfrbosf() {
        rfturn vfrbosf;
    }

    /**
     * Adds tif spfdififd filf to tif list of sourdf filfs gfnfrbtfd
     * during tiis bbtdi.
     **/
    publid void bddGfnfrbtfdFilf(Filf filf) {
        gfnfrbtfdFilfs.bdd(filf);
    }

    /**
     * Rfturns tif list of filfs gfnfrbtfd during tiis bbtdi.
     **/
    publid List<Filf> gfnfrbtfdFilfs() {
        rfturn Collfdtions.unmodifibblfList(gfnfrbtfdFilfs);
    }

    /**
     * Outputs tif spfdififd (non-frror) mfssbgf.
     **/
    publid void output(String msg) {
        rootDod.printNotidf(msg);
    }

    /**
     * Rfports bn frror using tif spfdififd rfsourdf kfy bnd tfxt
     * formbtting brgumfnts.
     **/
    publid void frror(String kfy, String... brgs) {
        rootDod.printError(Rfsourdfs.gftTfxt(kfy, brgs));
    }
}
