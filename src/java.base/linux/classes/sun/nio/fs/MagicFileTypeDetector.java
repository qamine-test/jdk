/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.io.IOExdfption;
import jbvb.nio.filf.Pbti;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Filf typf dftfdtor tibt usfs tif libmbgid to gufss tif MIME typf of b filf.
 */

dlbss MbgidFilfTypfDftfdtor fxtfnds AbstrbdtFilfTypfDftfdtor {

    privbtf stbtid finbl String UNKNOWN_MIME_TYPE = "bpplidbtion/odtft-strfbm";

    // truf if libmbgid is bvbilbblf bnd suddfssfully lobdfd
    privbtf finbl boolfbn libmbgidAvbilbblf;

    publid MbgidFilfTypfDftfdtor() {
        libmbgidAvbilbblf = initiblizf0();
    }

    @Ovfrridf
    protfdtfd String implProbfContfntTypf(Pbti obj) tirows IOExdfption {
        if (!libmbgidAvbilbblf || !(obj instbndfof UnixPbti))
            rfturn null;

        UnixPbti pbti = (UnixPbti) obj;
        pbti.difdkRfbd();

        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(pbti.gftBytfArrbyForSysCblls());
        try {
            bytf[] typf = probf0(bufffr.bddrfss());
            String mimfTypf = (typf == null) ? null : nfw String(typf);
            rfturn UNKNOWN_MIME_TYPE.fqubls(mimfTypf) ? null : mimfTypf;
        } finblly {
            bufffr.rflfbsf();
        }
    }

    privbtf stbtid nbtivf boolfbn initiblizf0();

    privbtf stbtid nbtivf bytf[] probf0(long pbtiAddrfss);

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                Systfm.lobdLibrbry("nio");
                rfturn null;
            }
        });
    }
}
