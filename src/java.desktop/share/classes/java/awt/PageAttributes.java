/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.util.Lodblf;

/**
 * A sft of bttributfs wiidi dontrol tif output of b printfd pbgf.
 * <p>
 * Instbndfs of tiis dlbss dontrol tif dolor stbtf, pbpfr sizf (mfdib typf),
 * orifntbtion, logidbl origin, print qublity, bnd rfsolution of fvfry
 * pbgf wiidi usfs tif instbndf. Attributf nbmfs brf domplibnt witi tif
 * Intfrnft Printing Protodol (IPP) 1.1 wifrf possiblf. Attributf vblufs
 * brf pbrtiblly domplibnt wifrf possiblf.
 * <p>
 * To usf b mftiod wiidi tbkfs bn innfr dlbss typf, pbss b rfffrfndf to
 * onf of tif donstbnt fiflds of tif innfr dlbss. Clifnt dodf dbnnot drfbtf
 * nfw instbndfs of tif innfr dlbss typfs bfdbusf nonf of tiosf dlbssfs
 * ibs b publid donstrudtor. For fxbmplf, to sft tif dolor stbtf to
 * monodiromf, usf tif following dodf:
 * <prf>
 * import jbvb.bwt.PbgfAttributfs;
 *
 * publid dlbss MonodiromfExbmplf {
 *     publid void sftMonodiromf(PbgfAttributfs pbgfAttributfs) {
 *         pbgfAttributfs.sftColor(PbgfAttributfs.ColorTypf.MONOCHROME);
 *     }
 * }
 * </prf>
 * <p>
 * Evfry IPP bttributf wiidi supports bn <i>bttributfNbmf</i>-dffbult vbluf
 * ibs b dorrfsponding <dodf>sft<i>bttributfNbmf</i>ToDffbult</dodf> mftiod.
 * Dffbult vbluf fiflds brf not providfd.
 *
 * @butior      Dbvid Mfndfnibll
 * @sindf 1.3
 */
publid finbl dlbss PbgfAttributfs implfmfnts Clonfbblf {
    /**
     * A typf-sbff fnumfrbtion of possiblf dolor stbtfs.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss ColorTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_COLOR = 0;
        privbtf stbtid finbl int I_MONOCHROME = 1;

        privbtf stbtid finbl String NAMES[] = {
            "dolor", "monodiromf"
        };

        /**
         * Tif ColorTypf instbndf to usf for spfdifying dolor printing.
         */
        publid stbtid finbl ColorTypf COLOR = nfw ColorTypf(I_COLOR);
        /**
         * Tif ColorTypf instbndf to usf for spfdifying monodiromf printing.
         */
        publid stbtid finbl ColorTypf MONOCHROME = nfw ColorTypf(I_MONOCHROME);

        privbtf ColorTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf pbpfr sizfs. Tifsf sizfs brf in
     * domplibndf witi IPP 1.1.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss MfdibTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_ISO_4A0 = 0;
        privbtf stbtid finbl int I_ISO_2A0 = 1;
        privbtf stbtid finbl int I_ISO_A0 = 2;
        privbtf stbtid finbl int I_ISO_A1 = 3;
        privbtf stbtid finbl int I_ISO_A2 = 4;
        privbtf stbtid finbl int I_ISO_A3 = 5;
        privbtf stbtid finbl int I_ISO_A4 = 6;
        privbtf stbtid finbl int I_ISO_A5 = 7;
        privbtf stbtid finbl int I_ISO_A6 = 8;
        privbtf stbtid finbl int I_ISO_A7 = 9;
        privbtf stbtid finbl int I_ISO_A8 = 10;
        privbtf stbtid finbl int I_ISO_A9 = 11;
        privbtf stbtid finbl int I_ISO_A10 = 12;
        privbtf stbtid finbl int I_ISO_B0 = 13;
        privbtf stbtid finbl int I_ISO_B1 = 14;
        privbtf stbtid finbl int I_ISO_B2 = 15;
        privbtf stbtid finbl int I_ISO_B3 = 16;
        privbtf stbtid finbl int I_ISO_B4 = 17;
        privbtf stbtid finbl int I_ISO_B5 = 18;
        privbtf stbtid finbl int I_ISO_B6 = 19;
        privbtf stbtid finbl int I_ISO_B7 = 20;
        privbtf stbtid finbl int I_ISO_B8 = 21;
        privbtf stbtid finbl int I_ISO_B9 = 22;
        privbtf stbtid finbl int I_ISO_B10 = 23;
        privbtf stbtid finbl int I_JIS_B0 = 24;
        privbtf stbtid finbl int I_JIS_B1 = 25;
        privbtf stbtid finbl int I_JIS_B2 = 26;
        privbtf stbtid finbl int I_JIS_B3 = 27;
        privbtf stbtid finbl int I_JIS_B4 = 28;
        privbtf stbtid finbl int I_JIS_B5 = 29;
        privbtf stbtid finbl int I_JIS_B6 = 30;
        privbtf stbtid finbl int I_JIS_B7 = 31;
        privbtf stbtid finbl int I_JIS_B8 = 32;
        privbtf stbtid finbl int I_JIS_B9 = 33;
        privbtf stbtid finbl int I_JIS_B10 = 34;
        privbtf stbtid finbl int I_ISO_C0 = 35;
        privbtf stbtid finbl int I_ISO_C1 = 36;
        privbtf stbtid finbl int I_ISO_C2 = 37;
        privbtf stbtid finbl int I_ISO_C3 = 38;
        privbtf stbtid finbl int I_ISO_C4 = 39;
        privbtf stbtid finbl int I_ISO_C5 = 40;
        privbtf stbtid finbl int I_ISO_C6 = 41;
        privbtf stbtid finbl int I_ISO_C7 = 42;
        privbtf stbtid finbl int I_ISO_C8 = 43;
        privbtf stbtid finbl int I_ISO_C9 = 44;
        privbtf stbtid finbl int I_ISO_C10 = 45;
        privbtf stbtid finbl int I_ISO_DESIGNATED_LONG = 46;
        privbtf stbtid finbl int I_EXECUTIVE = 47;
        privbtf stbtid finbl int I_FOLIO = 48;
        privbtf stbtid finbl int I_INVOICE = 49;
        privbtf stbtid finbl int I_LEDGER = 50;
        privbtf stbtid finbl int I_NA_LETTER = 51;
        privbtf stbtid finbl int I_NA_LEGAL = 52;
        privbtf stbtid finbl int I_QUARTO = 53;
        privbtf stbtid finbl int I_A = 54;
        privbtf stbtid finbl int I_B = 55;
        privbtf stbtid finbl int I_C = 56;
        privbtf stbtid finbl int I_D = 57;
        privbtf stbtid finbl int I_E = 58;
        privbtf stbtid finbl int I_NA_10X15_ENVELOPE = 59;
        privbtf stbtid finbl int I_NA_10X14_ENVELOPE = 60;
        privbtf stbtid finbl int I_NA_10X13_ENVELOPE = 61;
        privbtf stbtid finbl int I_NA_9X12_ENVELOPE = 62;
        privbtf stbtid finbl int I_NA_9X11_ENVELOPE = 63;
        privbtf stbtid finbl int I_NA_7X9_ENVELOPE = 64;
        privbtf stbtid finbl int I_NA_6X9_ENVELOPE = 65;
        privbtf stbtid finbl int I_NA_NUMBER_9_ENVELOPE = 66;
        privbtf stbtid finbl int I_NA_NUMBER_10_ENVELOPE = 67;
        privbtf stbtid finbl int I_NA_NUMBER_11_ENVELOPE = 68;
        privbtf stbtid finbl int I_NA_NUMBER_12_ENVELOPE = 69;
        privbtf stbtid finbl int I_NA_NUMBER_14_ENVELOPE = 70;
        privbtf stbtid finbl int I_INVITE_ENVELOPE = 71;
        privbtf stbtid finbl int I_ITALY_ENVELOPE = 72;
        privbtf stbtid finbl int I_MONARCH_ENVELOPE = 73;
        privbtf stbtid finbl int I_PERSONAL_ENVELOPE = 74;

        privbtf stbtid finbl String NAMES[] = {
            "iso-4b0", "iso-2b0", "iso-b0", "iso-b1", "iso-b2", "iso-b3",
            "iso-b4", "iso-b5", "iso-b6", "iso-b7", "iso-b8", "iso-b9",
            "iso-b10", "iso-b0", "iso-b1", "iso-b2", "iso-b3", "iso-b4",
            "iso-b5", "iso-b6", "iso-b7", "iso-b8", "iso-b9", "iso-b10",
            "jis-b0", "jis-b1", "jis-b2", "jis-b3", "jis-b4", "jis-b5",
            "jis-b6", "jis-b7", "jis-b8", "jis-b9", "jis-b10", "iso-d0",
            "iso-d1", "iso-d2", "iso-d3", "iso-d4", "iso-d5", "iso-d6",
            "iso-d7", "iso-d8", "iso-d9", "iso-d10", "iso-dfsignbtfd-long",
            "fxfdutivf", "folio", "invoidf", "lfdgfr", "nb-lfttfr", "nb-lfgbl",
            "qubrto", "b", "b", "d", "d", "f", "nb-10x15-fnvflopf",
            "nb-10x14-fnvflopf", "nb-10x13-fnvflopf", "nb-9x12-fnvflopf",
            "nb-9x11-fnvflopf", "nb-7x9-fnvflopf", "nb-6x9-fnvflopf",
            "nb-numbfr-9-fnvflopf", "nb-numbfr-10-fnvflopf",
            "nb-numbfr-11-fnvflopf", "nb-numbfr-12-fnvflopf",
            "nb-numbfr-14-fnvflopf", "invitf-fnvflopf", "itbly-fnvflopf",
            "monbrdi-fnvflopf", "pfrsonbl-fnvflopf"
        };

        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS 4A0, 1682 x 2378 mm.
         */
        publid stbtid finbl MfdibTypf ISO_4A0 = nfw MfdibTypf(I_ISO_4A0);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS 2A0, 1189 x 1682 mm.
         */
        publid stbtid finbl MfdibTypf ISO_2A0 = nfw MfdibTypf(I_ISO_2A0);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A0, 841 x 1189 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A0 = nfw MfdibTypf(I_ISO_A0);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A1, 594 x 841 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A1 = nfw MfdibTypf(I_ISO_A1);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A2, 420 x 594 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A2 = nfw MfdibTypf(I_ISO_A2);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A3, 297 x 420 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A3 = nfw MfdibTypf(I_ISO_A3);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A4, 210 x 297 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A4 = nfw MfdibTypf(I_ISO_A4);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A5, 148 x 210 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A5 = nfw MfdibTypf(I_ISO_A5);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A6, 105 x 148 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A6 = nfw MfdibTypf(I_ISO_A6);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A7, 74 x 105 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A7 = nfw MfdibTypf(I_ISO_A7);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A8, 52 x 74 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A8 = nfw MfdibTypf(I_ISO_A8);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A9, 37 x 52 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A9 = nfw MfdibTypf(I_ISO_A9);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN bnd JIS A10, 26 x 37 mm.
         */
        publid stbtid finbl MfdibTypf ISO_A10 = nfw MfdibTypf(I_ISO_A10);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B0, 1000 x 1414 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B0 = nfw MfdibTypf(I_ISO_B0);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B1, 707 x 1000 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B1 = nfw MfdibTypf(I_ISO_B1);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B2, 500 x 707 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B2 = nfw MfdibTypf(I_ISO_B2);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B3, 353 x 500 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B3 = nfw MfdibTypf(I_ISO_B3);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B4, 250 x 353 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B4 = nfw MfdibTypf(I_ISO_B4);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B5, 176 x 250 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B5 = nfw MfdibTypf(I_ISO_B5);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B6, 125 x 176 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B6 = nfw MfdibTypf(I_ISO_B6);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B7, 88 x 125 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B7 = nfw MfdibTypf(I_ISO_B7);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B8, 62 x 88 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B8 = nfw MfdibTypf(I_ISO_B8);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B9, 44 x 62 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B9 = nfw MfdibTypf(I_ISO_B9);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN B10, 31 x 44 mm.
         */
        publid stbtid finbl MfdibTypf ISO_B10 = nfw MfdibTypf(I_ISO_B10);
        /**
         * Tif MfdibTypf instbndf for JIS B0, 1030 x 1456 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B0 = nfw MfdibTypf(I_JIS_B0);
        /**
         * Tif MfdibTypf instbndf for JIS B1, 728 x 1030 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B1 = nfw MfdibTypf(I_JIS_B1);
        /**
         * Tif MfdibTypf instbndf for JIS B2, 515 x 728 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B2 = nfw MfdibTypf(I_JIS_B2);
        /**
         * Tif MfdibTypf instbndf for JIS B3, 364 x 515 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B3 = nfw MfdibTypf(I_JIS_B3);
        /**
         * Tif MfdibTypf instbndf for JIS B4, 257 x 364 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B4 = nfw MfdibTypf(I_JIS_B4);
        /**
         * Tif MfdibTypf instbndf for JIS B5, 182 x 257 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B5 = nfw MfdibTypf(I_JIS_B5);
        /**
         * Tif MfdibTypf instbndf for JIS B6, 128 x 182 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B6 = nfw MfdibTypf(I_JIS_B6);
        /**
         * Tif MfdibTypf instbndf for JIS B7, 91 x 128 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B7 = nfw MfdibTypf(I_JIS_B7);
        /**
         * Tif MfdibTypf instbndf for JIS B8, 64 x 91 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B8 = nfw MfdibTypf(I_JIS_B8);
        /**
         * Tif MfdibTypf instbndf for JIS B9, 45 x 64 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B9 = nfw MfdibTypf(I_JIS_B9);
        /**
         * Tif MfdibTypf instbndf for JIS B10, 32 x 45 mm.
         */
        publid stbtid finbl MfdibTypf JIS_B10 = nfw MfdibTypf(I_JIS_B10);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C0, 917 x 1297 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C0 = nfw MfdibTypf(I_ISO_C0);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C1, 648 x 917 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C1 = nfw MfdibTypf(I_ISO_C1);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C2, 458 x 648 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C2 = nfw MfdibTypf(I_ISO_C2);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C3, 324 x 458 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C3 = nfw MfdibTypf(I_ISO_C3);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C4, 229 x 324 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C4 = nfw MfdibTypf(I_ISO_C4);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C5, 162 x 229 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C5 = nfw MfdibTypf(I_ISO_C5);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C6, 114 x 162 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C6 = nfw MfdibTypf(I_ISO_C6);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C7, 81 x 114 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C7 = nfw MfdibTypf(I_ISO_C7);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C8, 57 x 81 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C8 = nfw MfdibTypf(I_ISO_C8);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C9, 40 x 57 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C9 = nfw MfdibTypf(I_ISO_C9);
        /**
         * Tif MfdibTypf instbndf for ISO/DIN C10, 28 x 40 mm.
         */
        publid stbtid finbl MfdibTypf ISO_C10 = nfw MfdibTypf(I_ISO_C10);
        /**
         * Tif MfdibTypf instbndf for ISO Dfsignbtfd Long, 110 x 220 mm.
         */
        publid stbtid finbl MfdibTypf ISO_DESIGNATED_LONG =
            nfw MfdibTypf(I_ISO_DESIGNATED_LONG);
        /**
         * Tif MfdibTypf instbndf for Exfdutivf, 7 1/4 x 10 1/2 in.
         */
        publid stbtid finbl MfdibTypf EXECUTIVE = nfw MfdibTypf(I_EXECUTIVE);
        /**
         * Tif MfdibTypf instbndf for Folio, 8 1/2 x 13 in.
         */
        publid stbtid finbl MfdibTypf FOLIO = nfw MfdibTypf(I_FOLIO);
        /**
         * Tif MfdibTypf instbndf for Invoidf, 5 1/2 x 8 1/2 in.
         */
        publid stbtid finbl MfdibTypf INVOICE = nfw MfdibTypf(I_INVOICE);
        /**
         * Tif MfdibTypf instbndf for Lfdgfr, 11 x 17 in.
         */
        publid stbtid finbl MfdibTypf LEDGER = nfw MfdibTypf(I_LEDGER);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn Lfttfr, 8 1/2 x 11 in.
         */
        publid stbtid finbl MfdibTypf NA_LETTER = nfw MfdibTypf(I_NA_LETTER);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn Lfgbl, 8 1/2 x 14 in.
         */
        publid stbtid finbl MfdibTypf NA_LEGAL = nfw MfdibTypf(I_NA_LEGAL);
        /**
         * Tif MfdibTypf instbndf for Qubrto, 215 x 275 mm.
         */
        publid stbtid finbl MfdibTypf QUARTO = nfw MfdibTypf(I_QUARTO);
        /**
         * Tif MfdibTypf instbndf for Enginffring A, 8 1/2 x 11 in.
         */
        publid stbtid finbl MfdibTypf A = nfw MfdibTypf(I_A);
        /**
         * Tif MfdibTypf instbndf for Enginffring B, 11 x 17 in.
         */
        publid stbtid finbl MfdibTypf B = nfw MfdibTypf(I_B);
        /**
         * Tif MfdibTypf instbndf for Enginffring C, 17 x 22 in.
         */
        publid stbtid finbl MfdibTypf C = nfw MfdibTypf(I_C);
        /**
         * Tif MfdibTypf instbndf for Enginffring D, 22 x 34 in.
         */
        publid stbtid finbl MfdibTypf D = nfw MfdibTypf(I_D);
        /**
         * Tif MfdibTypf instbndf for Enginffring E, 34 x 44 in.
         */
        publid stbtid finbl MfdibTypf E = nfw MfdibTypf(I_E);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 10 x 15 in.
         */
        publid stbtid finbl MfdibTypf NA_10X15_ENVELOPE =
            nfw MfdibTypf(I_NA_10X15_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 10 x 14 in.
         */
        publid stbtid finbl MfdibTypf NA_10X14_ENVELOPE =
            nfw MfdibTypf(I_NA_10X14_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 10 x 13 in.
         */
        publid stbtid finbl MfdibTypf NA_10X13_ENVELOPE =
            nfw MfdibTypf(I_NA_10X13_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 9 x 12 in.
         */
        publid stbtid finbl MfdibTypf NA_9X12_ENVELOPE =
            nfw MfdibTypf(I_NA_9X12_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 9 x 11 in.
         */
        publid stbtid finbl MfdibTypf NA_9X11_ENVELOPE =
            nfw MfdibTypf(I_NA_9X11_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 7 x 9 in.
         */
        publid stbtid finbl MfdibTypf NA_7X9_ENVELOPE =
            nfw MfdibTypf(I_NA_7X9_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn 6 x 9 in.
         */
        publid stbtid finbl MfdibTypf NA_6X9_ENVELOPE =
            nfw MfdibTypf(I_NA_6X9_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn #9 Businfss Envflopf,
         * 3 7/8 x 8 7/8 in.
         */
        publid stbtid finbl MfdibTypf NA_NUMBER_9_ENVELOPE =
            nfw MfdibTypf(I_NA_NUMBER_9_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn #10 Businfss Envflopf,
         * 4 1/8 x 9 1/2 in.
         */
        publid stbtid finbl MfdibTypf NA_NUMBER_10_ENVELOPE =
            nfw MfdibTypf(I_NA_NUMBER_10_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn #11 Businfss Envflopf,
         * 4 1/2 x 10 3/8 in.
         */
        publid stbtid finbl MfdibTypf NA_NUMBER_11_ENVELOPE =
            nfw MfdibTypf(I_NA_NUMBER_11_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn #12 Businfss Envflopf,
         * 4 3/4 x 11 in.
         */
        publid stbtid finbl MfdibTypf NA_NUMBER_12_ENVELOPE =
            nfw MfdibTypf(I_NA_NUMBER_12_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Norti Amfridbn #14 Businfss Envflopf,
         * 5 x 11 1/2 in.
         */
        publid stbtid finbl MfdibTypf NA_NUMBER_14_ENVELOPE =
            nfw MfdibTypf(I_NA_NUMBER_14_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Invitbtion Envflopf, 220 x 220 mm.
         */
        publid stbtid finbl MfdibTypf INVITE_ENVELOPE =
            nfw MfdibTypf(I_INVITE_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Itbly Envflopf, 110 x 230 mm.
         */
        publid stbtid finbl MfdibTypf ITALY_ENVELOPE =
            nfw MfdibTypf(I_ITALY_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for Monbrdi Envflopf, 3 7/8 x 7 1/2 in.
         */
        publid stbtid finbl MfdibTypf MONARCH_ENVELOPE =
            nfw MfdibTypf(I_MONARCH_ENVELOPE);
        /**
         * Tif MfdibTypf instbndf for 6 3/4 fnvflopf, 3 5/8 x 6 1/2 in.
         */
        publid stbtid finbl MfdibTypf PERSONAL_ENVELOPE =
            nfw MfdibTypf(I_PERSONAL_ENVELOPE);
        /**
         * An blibs for ISO_A0.
         */
        publid stbtid finbl MfdibTypf A0 = ISO_A0;
        /**
         * An blibs for ISO_A1.
         */
        publid stbtid finbl MfdibTypf A1 = ISO_A1;
        /**
         * An blibs for ISO_A2.
         */
        publid stbtid finbl MfdibTypf A2 = ISO_A2;
        /**
         * An blibs for ISO_A3.
         */
        publid stbtid finbl MfdibTypf A3 = ISO_A3;
        /**
         * An blibs for ISO_A4.
         */
        publid stbtid finbl MfdibTypf A4 = ISO_A4;
        /**
         * An blibs for ISO_A5.
         */
        publid stbtid finbl MfdibTypf A5 = ISO_A5;
        /**
         * An blibs for ISO_A6.
         */
        publid stbtid finbl MfdibTypf A6 = ISO_A6;
        /**
         * An blibs for ISO_A7.
         */
        publid stbtid finbl MfdibTypf A7 = ISO_A7;
        /**
         * An blibs for ISO_A8.
         */
        publid stbtid finbl MfdibTypf A8 = ISO_A8;
        /**
         * An blibs for ISO_A9.
         */
        publid stbtid finbl MfdibTypf A9 = ISO_A9;
        /**
         * An blibs for ISO_A10.
         */
        publid stbtid finbl MfdibTypf A10 = ISO_A10;
        /**
         * An blibs for ISO_B0.
         */
        publid stbtid finbl MfdibTypf B0 = ISO_B0;
        /**
         * An blibs for ISO_B1.
         */
        publid stbtid finbl MfdibTypf B1 = ISO_B1;
        /**
         * An blibs for ISO_B2.
         */
        publid stbtid finbl MfdibTypf B2 = ISO_B2;
        /**
         * An blibs for ISO_B3.
         */
        publid stbtid finbl MfdibTypf B3 = ISO_B3;
        /**
         * An blibs for ISO_B4.
         */
        publid stbtid finbl MfdibTypf B4 = ISO_B4;
        /**
         * An blibs for ISO_B4.
         */
        publid stbtid finbl MfdibTypf ISO_B4_ENVELOPE = ISO_B4;
        /**
         * An blibs for ISO_B5.
         */
        publid stbtid finbl MfdibTypf B5 = ISO_B5;
        /**
         * An blibs for ISO_B5.
         */
        publid stbtid finbl MfdibTypf ISO_B5_ENVELOPE = ISO_B5;
        /**
         * An blibs for ISO_B6.
         */
        publid stbtid finbl MfdibTypf B6 = ISO_B6;
        /**
         * An blibs for ISO_B7.
         */
        publid stbtid finbl MfdibTypf B7 = ISO_B7;
        /**
         * An blibs for ISO_B8.
         */
        publid stbtid finbl MfdibTypf B8 = ISO_B8;
        /**
         * An blibs for ISO_B9.
         */
        publid stbtid finbl MfdibTypf B9 = ISO_B9;
        /**
         * An blibs for ISO_B10.
         */
        publid stbtid finbl MfdibTypf B10 = ISO_B10;
        /**
         * An blibs for ISO_C0.
         */
        publid stbtid finbl MfdibTypf C0 = ISO_C0;
        /**
         * An blibs for ISO_C0.
         */
        publid stbtid finbl MfdibTypf ISO_C0_ENVELOPE = ISO_C0;
        /**
         * An blibs for ISO_C1.
         */
        publid stbtid finbl MfdibTypf C1 = ISO_C1;
        /**
         * An blibs for ISO_C1.
         */
        publid stbtid finbl MfdibTypf ISO_C1_ENVELOPE = ISO_C1;
        /**
         * An blibs for ISO_C2.
         */
        publid stbtid finbl MfdibTypf C2 = ISO_C2;
        /**
         * An blibs for ISO_C2.
         */
        publid stbtid finbl MfdibTypf ISO_C2_ENVELOPE = ISO_C2;
        /**
         * An blibs for ISO_C3.
         */
        publid stbtid finbl MfdibTypf C3 = ISO_C3;
        /**
         * An blibs for ISO_C3.
         */
        publid stbtid finbl MfdibTypf ISO_C3_ENVELOPE = ISO_C3;
        /**
         * An blibs for ISO_C4.
         */
        publid stbtid finbl MfdibTypf C4 = ISO_C4;
        /**
         * An blibs for ISO_C4.
         */
        publid stbtid finbl MfdibTypf ISO_C4_ENVELOPE = ISO_C4;
        /**
         * An blibs for ISO_C5.
         */
        publid stbtid finbl MfdibTypf C5 = ISO_C5;
        /**
         * An blibs for ISO_C5.
         */
        publid stbtid finbl MfdibTypf ISO_C5_ENVELOPE = ISO_C5;
        /**
         * An blibs for ISO_C6.
         */
        publid stbtid finbl MfdibTypf C6 = ISO_C6;
        /**
         * An blibs for ISO_C6.
         */
        publid stbtid finbl MfdibTypf ISO_C6_ENVELOPE = ISO_C6;
        /**
         * An blibs for ISO_C7.
         */
        publid stbtid finbl MfdibTypf C7 = ISO_C7;
        /**
         * An blibs for ISO_C7.
         */
        publid stbtid finbl MfdibTypf ISO_C7_ENVELOPE = ISO_C7;
        /**
         * An blibs for ISO_C8.
         */
        publid stbtid finbl MfdibTypf C8 = ISO_C8;
        /**
         * An blibs for ISO_C8.
         */
        publid stbtid finbl MfdibTypf ISO_C8_ENVELOPE = ISO_C8;
        /**
         * An blibs for ISO_C9.
         */
        publid stbtid finbl MfdibTypf C9 = ISO_C9;
        /**
         * An blibs for ISO_C9.
         */
        publid stbtid finbl MfdibTypf ISO_C9_ENVELOPE = ISO_C9;
        /**
         * An blibs for ISO_C10.
         */
        publid stbtid finbl MfdibTypf C10 = ISO_C10;
        /**
         * An blibs for ISO_C10.
         */
        publid stbtid finbl MfdibTypf ISO_C10_ENVELOPE = ISO_C10;
        /**
         * An blibs for ISO_DESIGNATED_LONG.
         */
        publid stbtid finbl MfdibTypf ISO_DESIGNATED_LONG_ENVELOPE =
                ISO_DESIGNATED_LONG;
        /**
         * An blibs for INVOICE.
         */
        publid stbtid finbl MfdibTypf STATEMENT = INVOICE;
        /**
         * An blibs for LEDGER.
         */
        publid stbtid finbl MfdibTypf TABLOID = LEDGER;
        /**
         * An blibs for NA_LETTER.
         */
        publid stbtid finbl MfdibTypf LETTER = NA_LETTER;
        /**
         * An blibs for NA_LETTER.
         */
        publid stbtid finbl MfdibTypf NOTE = NA_LETTER;
        /**
         * An blibs for NA_LEGAL.
         */
        publid stbtid finbl MfdibTypf LEGAL = NA_LEGAL;
        /**
         * An blibs for NA_10X15_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_10X15 = NA_10X15_ENVELOPE;
        /**
         * An blibs for NA_10X14_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_10X14 = NA_10X14_ENVELOPE;
        /**
         * An blibs for NA_10X13_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_10X13 = NA_10X13_ENVELOPE;
        /**
         * An blibs for NA_9X12_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_9X12 = NA_9X12_ENVELOPE;
        /**
         * An blibs for NA_9X11_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_9X11 = NA_9X11_ENVELOPE;
        /**
         * An blibs for NA_7X9_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_7X9 = NA_7X9_ENVELOPE;
        /**
         * An blibs for NA_6X9_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_6X9 = NA_6X9_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_9_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_9 = NA_NUMBER_9_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_10_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_10 = NA_NUMBER_10_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_11_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_11 = NA_NUMBER_11_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_12_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_12 = NA_NUMBER_12_ENVELOPE;
        /**
         * An blibs for NA_NUMBER_14_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_14 = NA_NUMBER_14_ENVELOPE;
        /**
         * An blibs for INVITE_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_INVITE = INVITE_ENVELOPE;
        /**
         * An blibs for ITALY_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_ITALY = ITALY_ENVELOPE;
        /**
         * An blibs for MONARCH_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_MONARCH = MONARCH_ENVELOPE;
        /**
         * An blibs for PERSONAL_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ENV_PERSONAL = PERSONAL_ENVELOPE;
        /**
         * An blibs for INVITE_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf INVITE = INVITE_ENVELOPE;
        /**
         * An blibs for ITALY_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf ITALY = ITALY_ENVELOPE;
        /**
         * An blibs for MONARCH_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf MONARCH = MONARCH_ENVELOPE;
        /**
         * An blibs for PERSONAL_ENVELOPE.
         */
        publid stbtid finbl MfdibTypf PERSONAL = PERSONAL_ENVELOPE;

        privbtf MfdibTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf orifntbtions. Tifsf orifntbtions
     * brf in pbrtibl domplibndf witi IPP 1.1.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss OrifntbtionRfqufstfdTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_PORTRAIT = 0;
        privbtf stbtid finbl int I_LANDSCAPE = 1;

        privbtf stbtid finbl String NAMES[] = {
            "portrbit", "lbndsdbpf"
        };

        /**
         * Tif OrifntbtionRfqufstfdTypf instbndf to usf for spfdifying b
         * portrbit orifntbtion.
         */
        publid stbtid finbl OrifntbtionRfqufstfdTypf PORTRAIT =
            nfw OrifntbtionRfqufstfdTypf(I_PORTRAIT);
        /**
         * Tif OrifntbtionRfqufstfdTypf instbndf to usf for spfdifying b
         * lbndsdbpf orifntbtion.
         */
        publid stbtid finbl OrifntbtionRfqufstfdTypf LANDSCAPE =
            nfw OrifntbtionRfqufstfdTypf(I_LANDSCAPE);

        privbtf OrifntbtionRfqufstfdTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf origins.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss OriginTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_PHYSICAL = 0;
        privbtf stbtid finbl int I_PRINTABLE = 1;

        privbtf stbtid finbl String NAMES[] = {
            "piysidbl", "printbblf"
        };

        /**
         * Tif OriginTypf instbndf to usf for spfdifying b piysidbl origin.
         */
        publid stbtid finbl OriginTypf PHYSICAL = nfw OriginTypf(I_PHYSICAL);
        /**
         * Tif OriginTypf instbndf to usf for spfdifying b printbblf origin.
         */
        publid stbtid finbl OriginTypf PRINTABLE = nfw OriginTypf(I_PRINTABLE);

        privbtf OriginTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf print qublitifs. Tifsf print
     * qublitifs brf in domplibndf witi IPP 1.1.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss PrintQublityTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_HIGH = 0;
        privbtf stbtid finbl int I_NORMAL = 1;
        privbtf stbtid finbl int I_DRAFT = 2;

        privbtf stbtid finbl String NAMES[] = {
            "iigi", "normbl", "drbft"
        };

        /**
         * Tif PrintQublityTypf instbndf to usf for spfdifying b iigi print
         * qublity.
         */
        publid stbtid finbl PrintQublityTypf HIGH =
            nfw PrintQublityTypf(I_HIGH);
        /**
         * Tif PrintQublityTypf instbndf to usf for spfdifying b normbl print
         * qublity.
         */
        publid stbtid finbl PrintQublityTypf NORMAL =
            nfw PrintQublityTypf(I_NORMAL);
        /**
         * Tif PrintQublityTypf instbndf to usf for spfdifying b drbft print
         * qublity.
         */
        publid stbtid finbl PrintQublityTypf DRAFT =
            nfw PrintQublityTypf(I_DRAFT);

        privbtf PrintQublityTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    privbtf ColorTypf dolor;
    privbtf MfdibTypf mfdib;
    privbtf OrifntbtionRfqufstfdTypf orifntbtionRfqufstfd;
    privbtf OriginTypf origin;
    privbtf PrintQublityTypf printQublity;
    privbtf int[] printfrRfsolution;

    /**
     * Construdts b PbgfAttributfs instbndf witi dffbult vblufs for fvfry
     * bttributf.
     */
    publid PbgfAttributfs() {
        sftColor(ColorTypf.MONOCHROME);
        sftMfdibToDffbult();
        sftOrifntbtionRfqufstfdToDffbult();
        sftOrigin(OriginTypf.PHYSICAL);
        sftPrintQublityToDffbult();
        sftPrintfrRfsolutionToDffbult();
    }

    /**
     * Construdts b PbgfAttributfs instbndf wiidi is b dopy of tif supplifd
     * PbgfAttributfs.
     *
     * @pbrbm   obj tif PbgfAttributfs to dopy.
     */
    publid PbgfAttributfs(PbgfAttributfs obj) {
        sft(obj);
    }

    /**
     * Construdts b PbgfAttributfs instbndf witi tif spfdififd vblufs for
     * fvfry bttributf.
     *
     * @pbrbm   dolor ColorTypf.COLOR or ColorTypf.MONOCHROME.
     * @pbrbm   mfdib onf of tif donstbnt fiflds of tif MfdibTypf dlbss.
     * @pbrbm   orifntbtionRfqufstfd OrifntbtionRfqufstfdTypf.PORTRAIT or
     *          OrifntbtionRfqufstfdTypf.LANDSCAPE.
     * @pbrbm   origin OriginTypf.PHYSICAL or OriginTypf.PRINTABLE
     * @pbrbm   printQublity PrintQublityTypf.DRAFT, PrintQublityTypf.NORMAL,
     *          or PrintQublityTypf.HIGH
     * @pbrbm   printfrRfsolution bn intfgfr brrby of 3 flfmfnts. Tif first
     *          flfmfnt must bf grfbtfr tibn 0. Tif sfdond flfmfnt must bf
     *          must bf grfbtfr tibn 0. Tif tiird flfmfnt must bf fitifr
     *          <dodf>3</dodf> or <dodf>4</dodf>.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid PbgfAttributfs(ColorTypf dolor, MfdibTypf mfdib,
                          OrifntbtionRfqufstfdTypf orifntbtionRfqufstfd,
                          OriginTypf origin, PrintQublityTypf printQublity,
                          int[] printfrRfsolution) {
        sftColor(dolor);
        sftMfdib(mfdib);
        sftOrifntbtionRfqufstfd(orifntbtionRfqufstfd);
        sftOrigin(origin);
        sftPrintQublity(printQublity);
        sftPrintfrRfsolution(printfrRfsolution);
    }

    /**
     * Crfbtfs bnd rfturns b dopy of tiis PbgfAttributfs.
     *
     * @rfturn  tif nfwly drfbtfd dopy. It is sbff to dbst tiis Objfdt into
     *          b PbgfAttributfs.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // Sindf wf implfmfnt Clonfbblf, tiis siould nfvfr ibppfn
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Sfts bll of tif bttributfs of tiis PbgfAttributfs to tif sbmf vblufs bs
     * tif bttributfs of obj.
     *
     * @pbrbm   obj tif PbgfAttributfs to dopy.
     */
    publid void sft(PbgfAttributfs obj) {
        dolor = obj.dolor;
        mfdib = obj.mfdib;
        orifntbtionRfqufstfd = obj.orifntbtionRfqufstfd;
        origin = obj.origin;
        printQublity = obj.printQublity;
        // okby bfdbusf wf nfvfr modify tif dontfnts of printfrRfsolution
        printfrRfsolution = obj.printfrRfsolution;
    }

    /**
     * Rfturns wiftifr pbgfs using tifsf bttributfs will bf rfndfrfd in
     * dolor or monodiromf. Tiis bttributf is updbtfd to tif vbluf diosfn
     * by tif usfr.
     *
     * @rfturn  ColorTypf.COLOR or ColorTypf.MONOCHROME.
     */
    publid ColorTypf gftColor() {
        rfturn dolor;
    }

    /**
     * Spfdififs wiftifr pbgfs using tifsf bttributfs will bf rfndfrfd in
     * dolor or monodiromf. Not spfdifying tiis bttributf is fquivblfnt to
     * spfdifying ColorTypf.MONOCHROME.
     *
     * @pbrbm   dolor ColorTypf.COLOR or ColorTypf.MONOCHROME.
     * @tirows  IllfgblArgumfntExdfption if dolor is null.
     */
    publid void sftColor(ColorTypf dolor) {
        if (dolor == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "dolor");
        }
        tiis.dolor = dolor;
    }

    /**
     * Rfturns tif pbpfr sizf for pbgfs using tifsf bttributfs. Tiis
     * bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  onf of tif donstbnt fiflds of tif MfdibTypf dlbss.
     */
    publid MfdibTypf gftMfdib() {
        rfturn mfdib;
    }

    /**
     * Spfdififs tif dfsirfd pbpfr sizf for pbgfs using tifsf bttributfs. Tif
     * bdtubl pbpfr sizf will bf dftfrminfd by tif limitbtions of tif tbrgft
     * printfr. If bn fxbdt mbtdi dbnnot bf found, bn implfmfntbtion will
     * dioosf tif dlosfst possiblf mbtdi. Not spfdifying tiis bttributf is
     * fquivblfnt to spfdifying tif dffbult sizf for tif dffbult lodblf. Tif
     * dffbult sizf for lodblfs in tif Unitfd Stbtfs bnd Cbnbdb is
     * MfdibTypf.NA_LETTER. Tif dffbult sizf for bll otifr lodblfs is
     * MfdibTypf.ISO_A4.
     *
     * @pbrbm   mfdib onf of tif donstbnt fiflds of tif MfdibTypf dlbss.
     * @tirows  IllfgblArgumfntExdfption if mfdib is null.
     */
    publid void sftMfdib(MfdibTypf mfdib) {
        if (mfdib == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "mfdib");
        }
        tiis.mfdib = mfdib;
    }

    /**
     * Sfts tif pbpfr sizf for pbgfs using tifsf bttributfs to tif dffbult
     * sizf for tif dffbult lodblf. Tif dffbult sizf for lodblfs in tif
     * Unitfd Stbtfs bnd Cbnbdb is MfdibTypf.NA_LETTER. Tif dffbult sizf for
     * bll otifr lodblfs is MfdibTypf.ISO_A4.
     */
    publid void sftMfdibToDffbult(){
        String dffbultCountry = Lodblf.gftDffbult().gftCountry();
        if (dffbultCountry != null &&
            (dffbultCountry.fqubls(Lodblf.US.gftCountry()) ||
             dffbultCountry.fqubls(Lodblf.CANADA.gftCountry()))) {
            sftMfdib(MfdibTypf.NA_LETTER);
        } flsf {
            sftMfdib(MfdibTypf.ISO_A4);
        }
    }

    /**
     * Rfturns tif print orifntbtion for pbgfs using tifsf bttributfs. Tiis
     * bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  OrifntbtionRfqufstfdTypf.PORTRAIT or
     *          OrifntbtionRfqufstfdTypf.LANDSCAPE.
     */
    publid OrifntbtionRfqufstfdTypf gftOrifntbtionRfqufstfd() {
        rfturn orifntbtionRfqufstfd;
    }

    /**
     * Spfdififs tif print orifntbtion for pbgfs using tifsf bttributfs. Not
     * spfdifying tif propfrty is fquivblfnt to spfdifying
     * OrifntbtionRfqufstfdTypf.PORTRAIT.
     *
     * @pbrbm   orifntbtionRfqufstfd OrifntbtionRfqufstfdTypf.PORTRAIT or
     *          OrifntbtionRfqufstfdTypf.LANDSCAPE.
     * @tirows  IllfgblArgumfntExdfption if orifntbtionRfqufstfd is null.
     */
    publid void sftOrifntbtionRfqufstfd(OrifntbtionRfqufstfdTypf
                                        orifntbtionRfqufstfd) {
        if (orifntbtionRfqufstfd == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "orifntbtionRfqufstfd");
        }
        tiis.orifntbtionRfqufstfd = orifntbtionRfqufstfd;
    }

    /**
     * Spfdififs tif print orifntbtion for pbgfs using tifsf bttributfs.
     * Spfdifying <dodf>3</dodf> dfnotfs portrbit. Spfdifying <dodf>4</dodf>
     * dfnotfs lbndsdbpf. Spfdifying bny otifr vbluf will gfnfrbtf bn
     * IllfgblArgumfntExdfption. Not spfdifying tif propfrty is fquivblfnt
     * to dblling sftOrifntbtionRfqufstfd(OrifntbtionRfqufstfdTypf.PORTRAIT).
     *
     * @pbrbm   orifntbtionRfqufstfd <dodf>3</dodf> or <dodf>4</dodf>
     * @tirows  IllfgblArgumfntExdfption if orifntbtionRfqufstfd is not
     *          <dodf>3</dodf> or <dodf>4</dodf>
     */
    publid void sftOrifntbtionRfqufstfd(int orifntbtionRfqufstfd) {
        switdi (orifntbtionRfqufstfd) {
          dbsf 3:
            sftOrifntbtionRfqufstfd(OrifntbtionRfqufstfdTypf.PORTRAIT);
            brfbk;
          dbsf 4:
            sftOrifntbtionRfqufstfd(OrifntbtionRfqufstfdTypf.LANDSCAPE);
            brfbk;
          dffbult:
            // Tiis will tirow bn IllfgblArgumfntExdfption
            sftOrifntbtionRfqufstfd(null);
            brfbk;
        }
    }

    /**
     * Sfts tif print orifntbtion for pbgfs using tifsf bttributfs to tif
     * dffbult. Tif dffbult orifntbtion is portrbit.
     */
    publid void sftOrifntbtionRfqufstfdToDffbult() {
        sftOrifntbtionRfqufstfd(OrifntbtionRfqufstfdTypf.PORTRAIT);
    }

    /**
     * Rfturns wiftifr drbwing bt (0, 0) to pbgfs using tifsf bttributfs
     * drbws bt tif uppfr-lfft dornfr of tif piysidbl pbgf, or bt tif
     * uppfr-lfft dornfr of tif printbblf brfb. (Notf tibt tifsf lodbtions
     * dould bf fquivblfnt.) Tiis bttributf dbnnot bf modififd by,
     * bnd is not subjfdt to bny limitbtions of, tif implfmfntbtion or tif
     * tbrgft printfr.
     *
     * @rfturn  OriginTypf.PHYSICAL or OriginTypf.PRINTABLE
     */
    publid OriginTypf gftOrigin() {
        rfturn origin;
    }

    /**
     * Spfdififs wiftifr drbwing bt (0, 0) to pbgfs using tifsf bttributfs
     * drbws bt tif uppfr-lfft dornfr of tif piysidbl pbgf, or bt tif
     * uppfr-lfft dornfr of tif printbblf brfb. (Notf tibt tifsf lodbtions
     * dould bf fquivblfnt.) Not spfdifying tif propfrty is fquivblfnt to
     * spfdifying OriginTypf.PHYSICAL.
     *
     * @pbrbm   origin OriginTypf.PHYSICAL or OriginTypf.PRINTABLE
     * @tirows  IllfgblArgumfntExdfption if origin is null.
     */
    publid void sftOrigin(OriginTypf origin) {
        if (origin == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "origin");
        }
        tiis.origin = origin;
    }

    /**
     * Rfturns tif print qublity for pbgfs using tifsf bttributfs. Tiis
     * bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  PrintQublityTypf.DRAFT, PrintQublityTypf.NORMAL, or
     *          PrintQublityTypf.HIGH
     */
    publid PrintQublityTypf gftPrintQublity() {
        rfturn printQublity;
    }

    /**
     * Spfdififs tif print qublity for pbgfs using tifsf bttributfs. Not
     * spfdifying tif propfrty is fquivblfnt to spfdifying
     * PrintQublityTypf.NORMAL.
     *
     * @pbrbm   printQublity PrintQublityTypf.DRAFT, PrintQublityTypf.NORMAL,
     *          or PrintQublityTypf.HIGH
     * @tirows  IllfgblArgumfntExdfption if printQublity is null.
     */
    publid void sftPrintQublity(PrintQublityTypf printQublity) {
        if (printQublity == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "printQublity");
        }
        tiis.printQublity = printQublity;
    }

    /**
     * Spfdififs tif print qublity for pbgfs using tifsf bttributfs.
     * Spfdifying <dodf>3</dodf> dfnotfs drbft. Spfdifying <dodf>4</dodf>
     * dfnotfs normbl. Spfdifying <dodf>5</dodf> dfnotfs iigi. Spfdifying
     * bny otifr vbluf will gfnfrbtf bn IllfgblArgumfntExdfption. Not
     * spfdifying tif propfrty is fquivblfnt to dblling
     * sftPrintQublity(PrintQublityTypf.NORMAL).
     *
     * @pbrbm   printQublity <dodf>3</dodf>, <dodf>4</dodf>, or <dodf>5</dodf>
     * @tirows  IllfgblArgumfntExdfption if printQublity is not <dodf>3
     *          </dodf>, <dodf>4</dodf>, or <dodf>5</dodf>
     */
    publid void sftPrintQublity(int printQublity) {
        switdi (printQublity) {
          dbsf 3:
            sftPrintQublity(PrintQublityTypf.DRAFT);
            brfbk;
          dbsf 4:
            sftPrintQublity(PrintQublityTypf.NORMAL);
            brfbk;
          dbsf 5:
            sftPrintQublity(PrintQublityTypf.HIGH);
            brfbk;
          dffbult:
            // Tiis will tirow bn IllfgblArgumfntExdfption
            sftPrintQublity(null);
            brfbk;
        }
    }

    /**
     * Sfts tif print qublity for pbgfs using tifsf bttributfs to tif dffbult.
     * Tif dffbult print qublity is normbl.
     */
    publid void sftPrintQublityToDffbult() {
        sftPrintQublity(PrintQublityTypf.NORMAL);
    }

    /**
     * Rfturns tif print rfsolution for pbgfs using tifsf bttributfs.
     * Indfx 0 of tif brrby spfdififs tif dross fffd dirfdtion rfsolution
     * (typidblly tif iorizontbl rfsolution). Indfx 1 of tif brrby spfdififs
     * tif fffd dirfdtion rfsolution (typidblly tif vfrtidbl rfsolution).
     * Indfx 2 of tif brrby spfdififs wiftifr tif rfsolutions brf in dots pfr
     * indi or dots pfr dfntimftfr. <dodf>3</dodf> dfnotfs dots pfr indi.
     * <dodf>4</dodf> dfnotfs dots pfr dfntimftfr.
     *
     * @rfturn  bn intfgfr brrby of 3 flfmfnts. Tif first
     *          flfmfnt must bf grfbtfr tibn 0. Tif sfdond flfmfnt must bf
     *          must bf grfbtfr tibn 0. Tif tiird flfmfnt must bf fitifr
     *          <dodf>3</dodf> or <dodf>4</dodf>.
     */
    publid int[] gftPrintfrRfsolution() {
        // Rfturn b dopy bfdbusf otifrwisf dlifnt dodf dould dirdumvfnt tif
        // tif difdks mbdf in sftPrintfrRfsolution by modifying tif
        // rfturnfd brrby.
        int[] dopy = nfw int[3];
        dopy[0] = printfrRfsolution[0];
        dopy[1] = printfrRfsolution[1];
        dopy[2] = printfrRfsolution[2];
        rfturn dopy;
    }

    /**
     * Spfdififs tif dfsirfd print rfsolution for pbgfs using tifsf bttributfs.
     * Tif bdtubl rfsolution will bf dftfrminfd by tif limitbtions of tif
     * implfmfntbtion bnd tif tbrgft printfr. Indfx 0 of tif brrby spfdififs
     * tif dross fffd dirfdtion rfsolution (typidblly tif iorizontbl
     * rfsolution). Indfx 1 of tif brrby spfdififs tif fffd dirfdtion
     * rfsolution (typidblly tif vfrtidbl rfsolution). Indfx 2 of tif brrby
     * spfdififs wiftifr tif rfsolutions brf in dots pfr indi or dots pfr
     * dfntimftfr. <dodf>3</dodf> dfnotfs dots pfr indi. <dodf>4</dodf>
     * dfnotfs dots pfr dfntimftfr. Notf tibt tif 1.1 printing implfmfntbtion
     * (Toolkit.gftPrintJob) rfquirfs tibt tif fffd bnd dross fffd rfsolutions
     * bf tif sbmf. Not spfdifying tif propfrty is fquivblfnt to dblling
     * sftPrintfrRfsolution(72).
     *
     * @pbrbm   printfrRfsolution bn intfgfr brrby of 3 flfmfnts. Tif first
     *          flfmfnt must bf grfbtfr tibn 0. Tif sfdond flfmfnt must bf
     *          must bf grfbtfr tibn 0. Tif tiird flfmfnt must bf fitifr
     *          <dodf>3</dodf> or <dodf>4</dodf>.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid void sftPrintfrRfsolution(int[] printfrRfsolution) {
        if (printfrRfsolution == null ||
            printfrRfsolution.lfngti != 3 ||
            printfrRfsolution[0] <= 0 ||
            printfrRfsolution[1] <= 0 ||
            (printfrRfsolution[2] != 3 && printfrRfsolution[2] != 4)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "printfrRfsolution");
        }
        // Storf b dopy bfdbusf otifrwisf dlifnt dodf dould dirdumvfnt tif
        // tif difdks mbdf bbovf by iolding b rfffrfndf to tif brrby bnd
        // modifying it bftfr dblling sftPrintfrRfsolution.
        int[] dopy = nfw int[3];
        dopy[0] = printfrRfsolution[0];
        dopy[1] = printfrRfsolution[1];
        dopy[2] = printfrRfsolution[2];
        tiis.printfrRfsolution = dopy;
    }

    /**
     * Spfdififs tif dfsirfd dross fffd bnd fffd print rfsolutions in dots pfr
     * indi for pbgfs using tifsf bttributfs. Tif sbmf vbluf is usfd for boti
     * rfsolutions. Tif bdtubl rfsolutions will bf dftfrminfd by tif
     * limitbtions of tif implfmfntbtion bnd tif tbrgft printfr. Not
     * spfdifying tif propfrty is fquivblfnt to spfdifying <dodf>72</dodf>.
     *
     * @pbrbm   printfrRfsolution bn intfgfr grfbtfr tibn 0.
     * @tirows  IllfgblArgumfntExdfption if printfrRfsolution is lfss tibn or
     *          fqubl to 0.
     */
    publid void sftPrintfrRfsolution(int printfrRfsolution) {
        sftPrintfrRfsolution(nfw int[] { printfrRfsolution, printfrRfsolution,
                                         3 } );
    }

    /**
     * Sfts tif printfr rfsolution for pbgfs using tifsf bttributfs to tif
     * dffbult. Tif dffbult is 72 dpi for boti tif fffd bnd dross fffd
     * rfsolutions.
     */
    publid void sftPrintfrRfsolutionToDffbult() {
        sftPrintfrRfsolution(72);
    }

    /**
     * Dftfrminfs wiftifr two PbgfAttributfs brf fqubl to fbdi otifr.
     * <p>
     * Two PbgfAttributfs brf fqubl if bnd only if fbdi of tifir bttributfs brf
     * fqubl. Attributfs of fnumfrbtion typf brf fqubl if bnd only if tif
     * fiflds rfffr to tif sbmf uniquf fnumfrbtion objfdt. Tiis mfbns tibt
     * bn blibsfd mfdib is fqubl to its undfrlying uniquf mfdib. Printfr
     * rfsolutions brf fqubl if bnd only if tif fffd rfsolution, dross fffd
     * rfsolution, bnd units brf fqubl.
     *
     * @pbrbm   obj tif objfdt wiosf fqublity will bf difdkfd.
     * @rfturn  wiftifr obj is fqubl to tiis PbgfAttributf bddording to tif
     *          bbovf dritfrib.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof PbgfAttributfs)) {
            rfturn fblsf;
        }

        PbgfAttributfs ris = (PbgfAttributfs)obj;

        rfturn (dolor == ris.dolor &&
                mfdib == ris.mfdib &&
                orifntbtionRfqufstfd == ris.orifntbtionRfqufstfd &&
                origin == ris.origin &&
                printQublity == ris.printQublity &&
                printfrRfsolution[0] == ris.printfrRfsolution[0] &&
                printfrRfsolution[1] == ris.printfrRfsolution[1] &&
                printfrRfsolution[2] == ris.printfrRfsolution[2]);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis PbgfAttributfs.
     *
     * @rfturn  tif ibsi dodf.
     */
    publid int ibsiCodf() {
        rfturn (dolor.ibsiCodf() << 31 ^
                mfdib.ibsiCodf() << 24 ^
                orifntbtionRfqufstfd.ibsiCodf() << 23 ^
                origin.ibsiCodf() << 22 ^
                printQublity.ibsiCodf() << 20 ^
                printfrRfsolution[2] >> 2 << 19 ^
                printfrRfsolution[1] << 10 ^
                printfrRfsolution[0]);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis PbgfAttributfs.
     *
     * @rfturn  tif string rfprfsfntbtion.
     */
    publid String toString() {
        // int[] printfrRfsolution = gftPrintfrRfsolution();
        rfturn "dolor=" + gftColor() + ",mfdib=" + gftMfdib() +
            ",orifntbtion-rfqufstfd=" + gftOrifntbtionRfqufstfd() +
            ",origin=" + gftOrigin() + ",print-qublity=" + gftPrintQublity() +
            ",printfr-rfsolution=[" + printfrRfsolution[0] + "," +
            printfrRfsolution[1] + "," + printfrRfsolution[2] + "]";
    }
}
