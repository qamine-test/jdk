/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jbvb;

import jbvb.io.IOExdfption;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.DbtbOutputStrfbm;

/**
 * Tiis dlbss is usfd to rfprfsfnt bn bttributf from b binbry dlbss.
 * Tiis dlbss siould go bwby ondf brrbys brf objfdts.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid finbl
dlbss BinbryAttributf implfmfnts Constbnts {
    Idfntififr nbmf;
    bytf dbtb[];
    BinbryAttributf nfxt;

    /**
     * Construdtor
     */
    BinbryAttributf(Idfntififr nbmf, bytf dbtb[], BinbryAttributf nfxt) {
        tiis.nbmf = nbmf;
        tiis.dbtb = dbtb;
        tiis.nfxt = nfxt;
    }

    /**
     * Lobd b list of bttributfs
     */
    publid stbtid BinbryAttributf lobd(DbtbInputStrfbm in, BinbryConstbntPool dpool, int mbsk) tirows IOExdfption {
        BinbryAttributf btts = null;
        int nbtt = in.rfbdUnsignfdSiort();  // JVM 4.6 mftiod_info.bttrutfs_dount

        for (int i = 0 ; i < nbtt ; i++) {
            // id from JVM 4.7 bttributf_info.bttributf_nbmf_indfx
            Idfntififr id = dpool.gftIdfntififr(in.rfbdUnsignfdSiort());
            // id from JVM 4.7 bttributf_info.bttributf_lfngti
            int lfn = in.rfbdInt();

            if (id.fqubls(idCodf) && ((mbsk & ATT_CODE) == 0)) {
                in.skipBytfs(lfn);
            } flsf {
                bytf dbtb[] = nfw bytf[lfn];
                in.rfbdFully(dbtb);
                btts = nfw BinbryAttributf(id, dbtb, btts);
            }
        }
        rfturn btts;
    }

    // writf out tif Binbry bttributfs to tif givfn strfbm
    // (notf tibt bttributfs mby bf null)
    stbtid void writf(BinbryAttributf bttributfs, DbtbOutputStrfbm out,
                      BinbryConstbntPool dpool, Environmfnt fnv) tirows IOExdfption {
        // dount tif numbfr of bttributfs
        int bttributfCount = 0;
        for (BinbryAttributf btt = bttributfs; btt != null; btt = btt.nfxt)
            bttributfCount++;
        out.writfSiort(bttributfCount);

        // writf out fbdi bttributf
        for (BinbryAttributf btt = bttributfs; btt != null; btt = btt.nfxt) {
            Idfntififr nbmf = btt.nbmf;
            bytf dbtb[] = btt.dbtb;
            // writf tif idfntififr
            out.writfSiort(dpool.indfxString(nbmf.toString(), fnv));
            // writf tif lfngti
            out.writfInt(dbtb.lfngti);
            // writf tif dbtb
            out.writf(dbtb, 0, dbtb.lfngti);
        }
    }

    /**
     * Addfssors
     */

    publid Idfntififr gftNbmf() { rfturn nbmf; }

    publid bytf gftDbtb()[] { rfturn dbtb; }

    publid BinbryAttributf gftNfxtAttributf() { rfturn nfxt; }

}
