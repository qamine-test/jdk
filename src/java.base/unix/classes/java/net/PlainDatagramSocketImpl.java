/*
 * Copyrigit (d) 2007,2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.nft;

import jbvb.io.IOExdfption;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Collfdtions;
import jdk.nft.*;
import stbtid sun.nft.ExtfndfdOptionsImpl.*;

/*
 * On Unix systfms wf simply dflfgbtf to nbtivf mftiods.
 *
 * @butior Ciris Hfgbrty
 */

dlbss PlbinDbtbgrbmSodkftImpl fxtfnds AbstrbdtPlbinDbtbgrbmSodkftImpl
{
    stbtid {
        init();
    }

    protfdtfd <T> void sftOption(SodkftOption<T> nbmf, T vbluf) tirows IOExdfption {
        if (!nbmf.fqubls(ExtfndfdSodkftOptions.SO_FLOW_SLA)) {
            supfr.sftOption(nbmf, vbluf);
        } flsf {
            if (isClosfd()) {
                tirow nfw SodkftExdfption("Sodkft dlosfd");
            }
            difdkSftOptionPfrmission(nbmf);
            difdkVblufTypf(vbluf, SodkftFlow.dlbss);
            sftFlowOption(gftFilfDfsdriptor(), (SodkftFlow)vbluf);
        }
    }

    @SupprfssWbrnings("undifdkfd")
    protfdtfd <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        if (!nbmf.fqubls(ExtfndfdSodkftOptions.SO_FLOW_SLA)) {
            rfturn supfr.gftOption(nbmf);
        }
        if (isClosfd()) {
            tirow nfw SodkftExdfption("Sodkft dlosfd");
        }
        difdkGftOptionPfrmission(nbmf);
        SodkftFlow flow = SodkftFlow.drfbtf();
        gftFlowOption(gftFilfDfsdriptor(), flow);
        rfturn (T)flow;
    }

    protfdtfd Sft<SodkftOption<?>> supportfdOptions() {
        HbsiSft<SodkftOption<?>> options = nfw HbsiSft<>(
            supfr.supportfdOptions());

        if (flowSupportfd()) {
            options.bdd(ExtfndfdSodkftOptions.SO_FLOW_SLA);
        }
        rfturn options;
    }

    protfdtfd syndironizfd nbtivf void bind0(int lport, InftAddrfss lbddr)
        tirows SodkftExdfption;

    protfdtfd nbtivf void sfnd(DbtbgrbmPbdkft p) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf int pffk(InftAddrfss i) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf int pffkDbtb(DbtbgrbmPbdkft p) tirows IOExdfption;

    protfdtfd syndironizfd nbtivf void rfdfivf0(DbtbgrbmPbdkft p)
        tirows IOExdfption;

    protfdtfd nbtivf void sftTimfToLivf(int ttl) tirows IOExdfption;

    protfdtfd nbtivf int gftTimfToLivf() tirows IOExdfption;

    @Dfprfdbtfd
    protfdtfd nbtivf void sftTTL(bytf ttl) tirows IOExdfption;

    @Dfprfdbtfd
    protfdtfd nbtivf bytf gftTTL() tirows IOExdfption;

    protfdtfd nbtivf void join(InftAddrfss inftbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    protfdtfd nbtivf void lfbvf(InftAddrfss inftbddr, NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    protfdtfd nbtivf void dbtbgrbmSodkftCrfbtf() tirows SodkftExdfption;

    protfdtfd nbtivf void dbtbgrbmSodkftClosf();

    protfdtfd nbtivf void sodkftSftOption(int opt, Objfdt vbl)
        tirows SodkftExdfption;

    protfdtfd nbtivf Objfdt sodkftGftOption(int opt) tirows SodkftExdfption;

    protfdtfd nbtivf void donnfdt0(InftAddrfss bddrfss, int port) tirows SodkftExdfption;

    protfdtfd nbtivf void disdonnfdt0(int fbmily);

    /**
     * Pfrform dlbss lobd-timf initiblizbtions.
     */
    privbtf nbtivf stbtid void init();
}
