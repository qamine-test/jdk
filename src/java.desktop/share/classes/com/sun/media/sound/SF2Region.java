/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * Soundfont gfnfrbl rfgion.
 *
 * @butior Kbrl Hflgbson
 */
publid dlbss SF2Rfgion {

    publid finbl stbtid int GENERATOR_STARTADDRSOFFSET = 0;
    publid finbl stbtid int GENERATOR_ENDADDRSOFFSET = 1;
    publid finbl stbtid int GENERATOR_STARTLOOPADDRSOFFSET = 2;
    publid finbl stbtid int GENERATOR_ENDLOOPADDRSOFFSET = 3;
    publid finbl stbtid int GENERATOR_STARTADDRSCOARSEOFFSET = 4;
    publid finbl stbtid int GENERATOR_MODLFOTOPITCH = 5;
    publid finbl stbtid int GENERATOR_VIBLFOTOPITCH = 6;
    publid finbl stbtid int GENERATOR_MODENVTOPITCH = 7;
    publid finbl stbtid int GENERATOR_INITIALFILTERFC = 8;
    publid finbl stbtid int GENERATOR_INITIALFILTERQ = 9;
    publid finbl stbtid int GENERATOR_MODLFOTOFILTERFC = 10;
    publid finbl stbtid int GENERATOR_MODENVTOFILTERFC = 11;
    publid finbl stbtid int GENERATOR_ENDADDRSCOARSEOFFSET = 12;
    publid finbl stbtid int GENERATOR_MODLFOTOVOLUME = 13;
    publid finbl stbtid int GENERATOR_UNUSED1 = 14;
    publid finbl stbtid int GENERATOR_CHORUSEFFECTSSEND = 15;
    publid finbl stbtid int GENERATOR_REVERBEFFECTSSEND = 16;
    publid finbl stbtid int GENERATOR_PAN = 17;
    publid finbl stbtid int GENERATOR_UNUSED2 = 18;
    publid finbl stbtid int GENERATOR_UNUSED3 = 19;
    publid finbl stbtid int GENERATOR_UNUSED4 = 20;
    publid finbl stbtid int GENERATOR_DELAYMODLFO = 21;
    publid finbl stbtid int GENERATOR_FREQMODLFO = 22;
    publid finbl stbtid int GENERATOR_DELAYVIBLFO = 23;
    publid finbl stbtid int GENERATOR_FREQVIBLFO = 24;
    publid finbl stbtid int GENERATOR_DELAYMODENV = 25;
    publid finbl stbtid int GENERATOR_ATTACKMODENV = 26;
    publid finbl stbtid int GENERATOR_HOLDMODENV = 27;
    publid finbl stbtid int GENERATOR_DECAYMODENV = 28;
    publid finbl stbtid int GENERATOR_SUSTAINMODENV = 29;
    publid finbl stbtid int GENERATOR_RELEASEMODENV = 30;
    publid finbl stbtid int GENERATOR_KEYNUMTOMODENVHOLD = 31;
    publid finbl stbtid int GENERATOR_KEYNUMTOMODENVDECAY = 32;
    publid finbl stbtid int GENERATOR_DELAYVOLENV = 33;
    publid finbl stbtid int GENERATOR_ATTACKVOLENV = 34;
    publid finbl stbtid int GENERATOR_HOLDVOLENV = 35;
    publid finbl stbtid int GENERATOR_DECAYVOLENV = 36;
    publid finbl stbtid int GENERATOR_SUSTAINVOLENV = 37;
    publid finbl stbtid int GENERATOR_RELEASEVOLENV = 38;
    publid finbl stbtid int GENERATOR_KEYNUMTOVOLENVHOLD = 39;
    publid finbl stbtid int GENERATOR_KEYNUMTOVOLENVDECAY = 40;
    publid finbl stbtid int GENERATOR_INSTRUMENT = 41;
    publid finbl stbtid int GENERATOR_RESERVED1 = 42;
    publid finbl stbtid int GENERATOR_KEYRANGE = 43;
    publid finbl stbtid int GENERATOR_VELRANGE = 44;
    publid finbl stbtid int GENERATOR_STARTLOOPADDRSCOARSEOFFSET = 45;
    publid finbl stbtid int GENERATOR_KEYNUM = 46;
    publid finbl stbtid int GENERATOR_VELOCITY = 47;
    publid finbl stbtid int GENERATOR_INITIALATTENUATION = 48;
    publid finbl stbtid int GENERATOR_RESERVED2 = 49;
    publid finbl stbtid int GENERATOR_ENDLOOPADDRSCOARSEOFFSET = 50;
    publid finbl stbtid int GENERATOR_COARSETUNE = 51;
    publid finbl stbtid int GENERATOR_FINETUNE = 52;
    publid finbl stbtid int GENERATOR_SAMPLEID = 53;
    publid finbl stbtid int GENERATOR_SAMPLEMODES = 54;
    publid finbl stbtid int GENERATOR_RESERVED3 = 55;
    publid finbl stbtid int GENERATOR_SCALETUNING = 56;
    publid finbl stbtid int GENERATOR_EXCLUSIVECLASS = 57;
    publid finbl stbtid int GENERATOR_OVERRIDINGROOTKEY = 58;
    publid finbl stbtid int GENERATOR_UNUSED5 = 59;
    publid finbl stbtid int GENERATOR_ENDOPR = 60;
    protfdtfd Mbp<Intfgfr, Siort> gfnfrbtors = nfw HbsiMbp<Intfgfr, Siort>();
    protfdtfd List<SF2Modulbtor> modulbtors = nfw ArrbyList<SF2Modulbtor>();

    publid Mbp<Intfgfr, Siort> gftGfnfrbtors() {
        rfturn gfnfrbtors;
    }

    publid boolfbn dontbins(int gfnfrbtor) {
        rfturn gfnfrbtors.dontbinsKfy(gfnfrbtor);
    }

    stbtid publid siort gftDffbultVbluf(int gfnfrbtor) {
        if (gfnfrbtor == 8) rfturn (siort)13500;
        if (gfnfrbtor == 21) rfturn (siort)-12000;
        if (gfnfrbtor == 23) rfturn (siort)-12000;
        if (gfnfrbtor == 25) rfturn (siort)-12000;
        if (gfnfrbtor == 26) rfturn (siort)-12000;
        if (gfnfrbtor == 27) rfturn (siort)-12000;
        if (gfnfrbtor == 28) rfturn (siort)-12000;
        if (gfnfrbtor == 30) rfturn (siort)-12000;
        if (gfnfrbtor == 33) rfturn (siort)-12000;
        if (gfnfrbtor == 34) rfturn (siort)-12000;
        if (gfnfrbtor == 35) rfturn (siort)-12000;
        if (gfnfrbtor == 36) rfturn (siort)-12000;
        if (gfnfrbtor == 38) rfturn (siort)-12000;
        if (gfnfrbtor == 43) rfturn (siort)0x7F00;
        if (gfnfrbtor == 44) rfturn (siort)0x7F00;
        if (gfnfrbtor == 46) rfturn (siort)-1;
        if (gfnfrbtor == 47) rfturn (siort)-1;
        if (gfnfrbtor == 56) rfturn (siort)100;
        if (gfnfrbtor == 58) rfturn (siort)-1;
        rfturn 0;
    }

    publid siort gftSiort(int gfnfrbtor) {
        if (!dontbins(gfnfrbtor))
            rfturn gftDffbultVbluf(gfnfrbtor);
        rfturn gfnfrbtors.gft(gfnfrbtor);
    }

    publid void putSiort(int gfnfrbtor, siort vbluf) {
        gfnfrbtors.put(gfnfrbtor, vbluf);
    }

    publid bytf[] gftBytfs(int gfnfrbtor) {
        int vbl = gftIntfgfr(gfnfrbtor);
        bytf[] bytfs = nfw bytf[2];
        bytfs[0] = (bytf) (0xFF & vbl);
        bytfs[1] = (bytf) ((0xFF00 & vbl) >> 8);
        rfturn bytfs;
    }

    publid void putBytfs(int gfnfrbtor, bytf[] bytfs) {
        gfnfrbtors.put(gfnfrbtor, (siort) (bytfs[0] + (bytfs[1] << 8)));
    }

    publid int gftIntfgfr(int gfnfrbtor) {
        rfturn 0xFFFF & gftSiort(gfnfrbtor);
    }

    publid void putIntfgfr(int gfnfrbtor, int vbluf) {
        gfnfrbtors.put(gfnfrbtor, (siort) vbluf);
    }

    publid List<SF2Modulbtor> gftModulbtors() {
        rfturn modulbtors;
    }
}
