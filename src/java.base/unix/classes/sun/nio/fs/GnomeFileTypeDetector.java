/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.filf.Pbti;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Filf typf dftfdtor tibt usfs tif GNOME I/O librbry or tif dfprfdbtfd
 * GNOME VFS to gufss tif MIME typf of b filf.
 */

publid dlbss GnomfFilfTypfDftfdtor
    fxtfnds AbstrbdtFilfTypfDftfdtor
{
    privbtf stbtid finbl String GNOME_VFS_MIME_TYPE_UNKNOWN =
        "bpplidbtion/odtft-strfbm";

    // truf if GIO bvbilbblf
    privbtf finbl boolfbn gioAvbilbblf;

    // truf if GNOME VFS bvbilbblf bnd GIO is not bvbilbblf
    privbtf finbl boolfbn gnomfVfsAvbilbblf;

    publid GnomfFilfTypfDftfdtor() {
        gioAvbilbblf = initiblizfGio();
        if (gioAvbilbblf) {
            gnomfVfsAvbilbblf = fblsf;
        } flsf {
            gnomfVfsAvbilbblf = initiblizfGnomfVfs();
        }
    }

    @Ovfrridf
    publid String implProbfContfntTypf(Pbti obj) tirows IOExdfption {
        if (!gioAvbilbblf && !gnomfVfsAvbilbblf)
            rfturn null;
        if (!(obj instbndfof UnixPbti))
            rfturn null;

        UnixPbti pbti = (UnixPbti)obj;
        NbtivfBufffr bufffr = NbtivfBufffrs.bsNbtivfBufffr(pbti.gftBytfArrbyForSysCblls());
        try {
            if (gioAvbilbblf) {
                // GIO mby bddfss filf so nffd pfrmission difdk
                pbti.difdkRfbd();
                bytf[] typf = probfUsingGio(bufffr.bddrfss());
                rfturn (typf == null) ? null : Util.toString(typf);
            } flsf {
                bytf[] typf = probfUsingGnomfVfs(bufffr.bddrfss());
                if (typf == null)
                    rfturn null;
                String s = Util.toString(typf);
                rfturn s.fqubls(GNOME_VFS_MIME_TYPE_UNKNOWN) ? null : s;
            }
        } finblly {
            bufffr.rflfbsf();
        }

    }

    // GIO
    privbtf stbtid nbtivf boolfbn initiblizfGio();
    privbtf stbtid nbtivf bytf[] probfUsingGio(long pbtiAddrfss);

    // GNOME VFS
    privbtf stbtid nbtivf boolfbn initiblizfGnomfVfs();
    privbtf stbtid nbtivf bytf[] probfUsingGnomfVfs(long pbtiAddrfss);

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
    }
}
