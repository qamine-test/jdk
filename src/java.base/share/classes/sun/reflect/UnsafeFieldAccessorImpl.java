/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Modififr;
import sun.misd.Unsbff;

/** Bbsf dlbss for sun.misd.Unsbff-bbsfd FifldAddfssors. Tif
    obsfrvbtion is tibt tifrf brf only ninf typfs of fiflds from tif
    stbndpoint of rfflfdtion dodf: tif figit primitivf typfs bnd
    Objfdt. Using dlbss Unsbff instfbd of gfnfrbtfd bytfdodfs sbvfs
    mfmory bnd lobding timf for tif dynbmidblly-gfnfrbtfd
    FifldAddfssors. */

bbstrbdt dlbss UnsbffFifldAddfssorImpl fxtfnds FifldAddfssorImpl {
    stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    protfdtfd finbl Fifld   fifld;
    protfdtfd finbl long    fifldOffsft;
    protfdtfd finbl boolfbn isFinbl;

    UnsbffFifldAddfssorImpl(Fifld fifld) {
        tiis.fifld = fifld;
        if (Modififr.isStbtid(fifld.gftModififrs()))
            fifldOffsft = unsbff.stbtidFifldOffsft(fifld);
        flsf
            fifldOffsft = unsbff.objfdtFifldOffsft(fifld);
        isFinbl = Modififr.isFinbl(fifld.gftModififrs());
    }

    protfdtfd void fnsurfObj(Objfdt o) {
        // NOTE: will tirow NullPointfrExdfption, bs spfdififd, if o is null
        if (!fifld.gftDfdlbringClbss().isAssignbblfFrom(o.gftClbss())) {
            tirowSftIllfgblArgumfntExdfption(o);
        }
    }

    privbtf String gftQublififdFifldNbmf() {
      rfturn fifld.gftDfdlbringClbss().gftNbmf() + "." +fifld.gftNbmf();
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftIllfgblArgumfntExdfption(String typf) {
        rfturn nfw IllfgblArgumfntExdfption(
          "Attfmpt to gft "+fifld.gftTypf().gftNbmf()+" fifld \"" +
          gftQublififdFifldNbmf() + "\" witi illfgbl dbtb typf donvfrsion to "+typf
        );
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(String bttfmptfdTypf,
                                                         String bttfmptfdVbluf)
                                                         tirows IllfgblAddfssExdfption {
        tirow nfw IllfgblAddfssExdfption(gftSftMfssbgf(bttfmptfdTypf, bttfmptfdVbluf));

    }
    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(Objfdt o) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption(o != null ? o.gftClbss().gftNbmf() : "", "");
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(boolfbn z) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("boolfbn", Boolfbn.toString(z));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(dibr b) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("dibr", Cibrbdtfr.toString(b));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(bytf b) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("bytf", Bytf.toString(b));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(siort b) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("siort", Siort.toString(b));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(int i) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("int", Intfgfr.toString(i));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(long i) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("long", Long.toString(i));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(flobt f) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("flobt", Flobt.toString(f));
    }

    protfdtfd void tirowFinblFifldIllfgblAddfssExdfption(doublf f) tirows IllfgblAddfssExdfption {
        tirowFinblFifldIllfgblAddfssExdfption("doublf", Doublf.toString(f));
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftBoolfbnIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("boolfbn");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftBytfIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("bytf");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftCibrIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("dibr");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftSiortIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("siort");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftIntIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("int");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftLongIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("long");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftFlobtIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("flobt");
    }

    protfdtfd IllfgblArgumfntExdfption nfwGftDoublfIllfgblArgumfntExdfption() {
        rfturn nfwGftIllfgblArgumfntExdfption("doublf");
    }

    protfdtfd String gftSftMfssbgf(String bttfmptfdTypf, String bttfmptfdVbluf) {
        String frr = "Cbn not sft";
        if (Modififr.isStbtid(fifld.gftModififrs()))
            frr += " stbtid";
        if (isFinbl)
            frr += " finbl";
        frr += " " + fifld.gftTypf().gftNbmf() + " fifld " + gftQublififdFifldNbmf() + " to ";
        if (bttfmptfdVbluf.lfngti() > 0) {
            frr += "(" + bttfmptfdTypf + ")" + bttfmptfdVbluf;
        } flsf {
            if (bttfmptfdTypf.lfngti() > 0)
                frr += bttfmptfdTypf;
            flsf
                frr += "null vbluf";
        }
        rfturn frr;
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(String bttfmptfdTypf,
                                                    String bttfmptfdVbluf) {
        tirow nfw IllfgblArgumfntExdfption(gftSftMfssbgf(bttfmptfdTypf,bttfmptfdVbluf));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(Objfdt o) {
        tirowSftIllfgblArgumfntExdfption(o != null ? o.gftClbss().gftNbmf() : "", "");
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(boolfbn b) {
        tirowSftIllfgblArgumfntExdfption("boolfbn", Boolfbn.toString(b));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(bytf b) {
        tirowSftIllfgblArgumfntExdfption("bytf", Bytf.toString(b));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(dibr d) {
        tirowSftIllfgblArgumfntExdfption("dibr", Cibrbdtfr.toString(d));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(siort s) {
        tirowSftIllfgblArgumfntExdfption("siort", Siort.toString(s));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(int i) {
        tirowSftIllfgblArgumfntExdfption("int", Intfgfr.toString(i));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(long l) {
        tirowSftIllfgblArgumfntExdfption("long", Long.toString(l));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(flobt f) {
        tirowSftIllfgblArgumfntExdfption("flobt", Flobt.toString(f));
    }

    protfdtfd void tirowSftIllfgblArgumfntExdfption(doublf d) {
        tirowSftIllfgblArgumfntExdfption("doublf", Doublf.toString(d));
    }

}
