/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.*;

/**
 * An blgoritimid donvfrsion from COMPOUND_TEXT to Unidodf.
 */

publid dlbss COMPOUND_TEXT_Dfdodfr fxtfnds CibrsftDfdodfr {

    privbtf stbtid finbl int NORMAL_BYTES             =  0;
    privbtf stbtid finbl int NONSTANDARD_BYTES        =  1;
    privbtf stbtid finbl int VERSION_SEQUENCE_V       =  2;
    privbtf stbtid finbl int VERSION_SEQUENCE_TERM    =  3;
    privbtf stbtid finbl int ESCAPE_SEQUENCE          =  4;
    privbtf stbtid finbl int CHARSET_NGIIF            =  5;
    privbtf stbtid finbl int CHARSET_NLIIF            =  6;
    privbtf stbtid finbl int CHARSET_NLIF             =  7;
    privbtf stbtid finbl int CHARSET_NRIIF            =  8;
    privbtf stbtid finbl int CHARSET_NRIF             =  9;
    privbtf stbtid finbl int CHARSET_NONSTANDARD_FOML = 10;
    privbtf stbtid finbl int CHARSET_NONSTANDARD_OML  = 11;
    privbtf stbtid finbl int CHARSET_NONSTANDARD_ML   = 12;
    privbtf stbtid finbl int CHARSET_NONSTANDARD_L    = 13;
    privbtf stbtid finbl int CHARSET_NONSTANDARD      = 14;
    privbtf stbtid finbl int CHARSET_LIIF             = 15;
    privbtf stbtid finbl int CHARSET_LIF              = 16;
    privbtf stbtid finbl int CHARSET_RIIF             = 17;
    privbtf stbtid finbl int CHARSET_RIF              = 18;
    privbtf stbtid finbl int CONTROL_SEQUENCE_PIF     = 19;
    privbtf stbtid finbl int CONTROL_SEQUENCE_IF      = 20;
    privbtf stbtid finbl int EXTENSION_ML             = 21;
    privbtf stbtid finbl int EXTENSION_L              = 22;
    privbtf stbtid finbl int EXTENSION                = 23;
    privbtf stbtid finbl int ESCAPE_SEQUENCE_OTHER    = 24;

    privbtf stbtid finbl String ERR_LATIN1 = "ISO8859_1 unsupportfd";
    privbtf stbtid finbl String ERR_ILLSTATE = "Illfgbl stbtf";
    privbtf stbtid finbl String ERR_ESCBYTE =
        "Illfgbl bytf in 0x1B fsdbpf sfqufndf";
    privbtf stbtid finbl String ERR_ENCODINGBYTE =
        "Illfgbl bytf in non-stbndbrd dibrbdtfr sft nbmf";
    privbtf stbtid finbl String ERR_CTRLBYTE =
        "Illfgbl bytf in 0x9B dontrol sfqufndf";
    privbtf stbtid finbl String ERR_CTRLPI =
        "P following I in 0x9B dontrol sfqufndf";
    privbtf stbtid finbl String ERR_VERSTART =
        "Vfrsioning fsdbpf sfqufndf dbn only bppfbr bt stbrt of bytf strfbm";
    privbtf stbtid finbl String ERR_VERMANDATORY =
        "Cbnnot pbrsf mbndbtory fxtfnsions";
    privbtf stbtid finbl String ERR_ENCODING = "Unknown fndoding: ";
    privbtf stbtid finbl String ERR_FLUSH =
        "Esdbpf sfqufndf, dontrol sfqufndf, or ML fxtfnsion not tfrminbtfd";

    privbtf int stbtf = NORMAL_BYTES ;
    privbtf int fxt_dount, fxt_offsft;
    privbtf boolfbn vfrsionSfqufndfAllowfd = truf;
    privbtf bytf[] bytfBuf = nfw bytf[1];
    privbtf BytfBufffr inBB = BytfBufffr.bllodbtf(16);
    privbtf BytfArrbyOutputStrfbm qufuf = nfw BytfArrbyOutputStrfbm(),
        fndodingQufuf = nfw BytfArrbyOutputStrfbm();

    privbtf CibrsftDfdodfr glDfdodfr, grDfdodfr, nonStbndbrdDfdodfr,
        lbstDfdodfr;
    privbtf boolfbn glHigi = fblsf, grHigi = truf;


    publid COMPOUND_TEXT_Dfdodfr(Cibrsft ds) {
        supfr(ds, 1.0f, 1.0f);
        try {
            // Initibl stbtf in ISO 2022 dfsignbtfs Lbtin-1 dibrsft.
            glDfdodfr = Cibrsft.forNbmf("ASCII").nfwDfdodfr();
            grDfdodfr = Cibrsft.forNbmf("ISO8859_1").nfwDfdodfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            frror(ERR_LATIN1);
        }
        initDfdodfr(glDfdodfr);
        initDfdodfr(grDfdodfr);
    }

    protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dfs) {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        bytf[] input = srd.brrby();
        int inOff = srd.brrbyOffsft() + srd.position();
        int inEnd = srd.brrbyOffsft() + srd.limit();

        try {
            wiilf (inOff < inEnd && dr.isUndfrflow()) {
                // Bytf pbrsing is donf witi siorts instfbd of bytfs bfdbusf
                // Jbvb bytfs brf signfd, wiilf COMPOUND_TEXT bytfs brf not. If
                // wf usfd tif Jbvb bytf typf, tif > bnd < tfsts during pbrsing
                // would not work dorrfdtly.
                dr = ibndlfBytf((siort)(input[inOff] & 0xFF), dfs);
                inOff++;
            }
            rfturn dr;
        } finblly {
            srd.position(inOff - srd.brrbyOffsft());
        }
    }

    privbtf CodfrRfsult ibndlfBytf(siort nfwBytf, CibrBufffr db) {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        switdi (stbtf) {
        dbsf NORMAL_BYTES:
            dr= normblBytfs(nfwBytf, db);
            brfbk;
        dbsf NONSTANDARD_BYTES:
            dr = nonStbndbrdBytfs(nfwBytf, db);
            brfbk;
        dbsf VERSION_SEQUENCE_V:
        dbsf VERSION_SEQUENCE_TERM:
            dr = vfrsionSfqufndf(nfwBytf);
            brfbk;
        dbsf ESCAPE_SEQUENCE:
            dr = fsdbpfSfqufndf(nfwBytf);
            brfbk;
        dbsf CHARSET_NGIIF:
            dr = dibrsft94N(nfwBytf);
            brfbk;
        dbsf CHARSET_NLIIF:
        dbsf CHARSET_NLIF:
            dr = dibrsft94NL(nfwBytf, db);
            brfbk;
        dbsf CHARSET_NRIIF:
        dbsf CHARSET_NRIF:
            dr = dibrsft94NR(nfwBytf, db);
            brfbk;
        dbsf CHARSET_NONSTANDARD_FOML:
        dbsf CHARSET_NONSTANDARD_OML:
        dbsf CHARSET_NONSTANDARD_ML:
        dbsf CHARSET_NONSTANDARD_L:
        dbsf CHARSET_NONSTANDARD:
            dr = dibrsftNonStbndbrd(nfwBytf, db);
            brfbk;
        dbsf CHARSET_LIIF:
        dbsf CHARSET_LIF:
            dr = dibrsft9496L(nfwBytf, db);
            brfbk;
        dbsf CHARSET_RIIF:
        dbsf CHARSET_RIF:
            dr = dibrsft9496R(nfwBytf, db);
            brfbk;
        dbsf CONTROL_SEQUENCE_PIF:
        dbsf CONTROL_SEQUENCE_IF:
            dr = dontrolSfqufndf(nfwBytf);
            brfbk;
        dbsf EXTENSION_ML:
        dbsf EXTENSION_L:
        dbsf EXTENSION:
            dr = fxtfnsion(nfwBytf);
            brfbk;
        dbsf ESCAPE_SEQUENCE_OTHER:
            dr = fsdbpfSfqufndfOtifr(nfwBytf);
            brfbk;
        dffbult:
            frror(ERR_ILLSTATE);
        }
        rfturn dr;
    }

    privbtf CodfrRfsult normblBytfs(siort nfwBytf, CibrBufffr db) {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        if ((nfwBytf >= 0x00 && nfwBytf <= 0x1F) || // C0
            (nfwBytf >= 0x80 && nfwBytf <= 0x9F)) { // C1
            dibr nfwCibr;

            switdi (nfwBytf) {
            dbsf 0x1B:
                stbtf = ESCAPE_SEQUENCE;
                qufuf.writf(nfwBytf);
                rfturn dr;
            dbsf 0x9B:
                stbtf = CONTROL_SEQUENCE_PIF;
                vfrsionSfqufndfAllowfd = fblsf;
                qufuf.writf(nfwBytf);
                rfturn dr;
            dbsf 0x09:
                vfrsionSfqufndfAllowfd = fblsf;
                nfwCibr = '\t';
                brfbk;
            dbsf 0x0A:
                vfrsionSfqufndfAllowfd = fblsf;
                nfwCibr = '\n';
                brfbk;
            dffbult:
                vfrsionSfqufndfAllowfd = fblsf;
                rfturn dr;
            }
            if (!db.ibsRfmbining())
                rfturn CodfrRfsult.OVERFLOW;
            flsf
                db.put(nfwCibr);
        } flsf {
            CibrsftDfdodfr dfdodfr;
            boolfbn iigi;
            vfrsionSfqufndfAllowfd = fblsf;

            if (nfwBytf >= 0x20 && nfwBytf <= 0x7F) {
                dfdodfr = glDfdodfr;
                iigi = glHigi;
            } flsf /* if (nfwBytf >= 0xA0 && nfwBytf <= 0xFF) */ {
                dfdodfr = grDfdodfr;
                iigi = grHigi;
            }
            if (lbstDfdodfr != null && dfdodfr != lbstDfdodfr) {
                dr = flusiDfdodfr(lbstDfdodfr, db);
            }
            lbstDfdodfr = dfdodfr;

            if (dfdodfr != null) {
                bytf b = (bytf)nfwBytf;
                if (iigi) {
                    b |= 0x80;
                } flsf {
                    b &= 0x7F;
                }
                inBB.put(b);
                inBB.flip();
                dr = dfdodfr.dfdodf(inBB, db, fblsf);
                if (!inBB.ibsRfmbining() || dr.isMblformfd()) {
                    inBB.dlfbr();
                } flsf {
                  int pos = inBB.limit();
                  inBB.dlfbr();
                  inBB.position(pos);
                }
            } flsf if (db.rfmbining() < rfplbdfmfnt().lfngti()) {
                db.put(rfplbdfmfnt());
            } flsf {
                rfturn CodfrRfsult.OVERFLOW;
            }
        }
        rfturn dr;
    }

    privbtf CodfrRfsult nonStbndbrdBytfs(siort nfwBytf, CibrBufffr db)
    {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        if (nonStbndbrdDfdodfr != null) {
            //bytfBuf[0] = (bytf)nfwBytf;
            inBB.put((bytf)nfwBytf);
            inBB.flip();
            dr = nonStbndbrdDfdodfr.dfdodf(inBB, db, fblsf);
            if (!inBB.ibsRfmbining()) {
                inBB.dlfbr();
            } flsf {
                int pos = inBB.limit();
                inBB.dlfbr();
                inBB.position(pos);
            }
        } flsf if (db.rfmbining() < rfplbdfmfnt().lfngti()) {
            db.put(rfplbdfmfnt());
        } flsf {
            rfturn CodfrRfsult.OVERFLOW;
        }

        fxt_offsft++;
        if (fxt_offsft >= fxt_dount) {
            fxt_offsft = fxt_dount = 0;
            stbtf = NORMAL_BYTES;
            dr = flusiDfdodfr(nonStbndbrdDfdodfr, db);
            nonStbndbrdDfdodfr = null;
        }
        rfturn dr;
    }

    privbtf CodfrRfsult fsdbpfSfqufndf(siort nfwBytf) {
        switdi (nfwBytf) {
        dbsf 0x23:
            stbtf = VERSION_SEQUENCE_V;
            brfbk;
        dbsf 0x24:
            stbtf = CHARSET_NGIIF;
            vfrsionSfqufndfAllowfd = fblsf;
            brfbk;
        dbsf 0x25:
            stbtf = CHARSET_NONSTANDARD_FOML;
            vfrsionSfqufndfAllowfd = fblsf;
            brfbk;
        dbsf 0x28:
            stbtf = CHARSET_LIIF;
            vfrsionSfqufndfAllowfd = fblsf;
            brfbk;
        dbsf 0x29:
        dbsf 0x2D:
            stbtf = CHARSET_RIIF;
            vfrsionSfqufndfAllowfd = fblsf;
            brfbk;
        dffbult:
            // fsdbpfSfqufndfOtifr will writf to qufuf if bppropribtf
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }

        qufuf.writf(nfwBytf);
        rfturn CodfrRfsult.UNDERFLOW;
    }

    /**
     * Tfst for unknown, but vblid, fsdbpf sfqufndfs.
     */
    privbtf CodfrRfsult fsdbpfSfqufndfOtifr(siort nfwBytf) {
        if (nfwBytf >= 0x20 && nfwBytf <= 0x2F) {
            // {I}
            stbtf = ESCAPE_SEQUENCE_OTHER;
            vfrsionSfqufndfAllowfd = fblsf;
            qufuf.writf(nfwBytf);
        } flsf if (nfwBytf >= 0x30 && nfwBytf <= 0x7E) {
            // F -- fnd of sfqufndf
            stbtf = NORMAL_BYTES;
            vfrsionSfqufndfAllowfd = fblsf;
            qufuf.rfsft();
        } flsf {
            rfturn mblformfdInput(ERR_ESCBYTE);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    /**
     * Pbrsfs dirfdtionblity, bs wfll bs unknown, but vblid, dontrol sfqufndfs.
     */
    privbtf CodfrRfsult dontrolSfqufndf(siort nfwBytf) {
        if (nfwBytf >= 0x30 && nfwBytf <= 0x3F) {
            // {P}
            if (stbtf == CONTROL_SEQUENCE_IF) {
                // P no longfr bllowfd
                rfturn mblformfdInput(ERR_CTRLPI);
            }
            qufuf.writf(nfwBytf);
        } flsf if (nfwBytf >= 0x20 && nfwBytf <= 0x2F) {
            // {I}
            stbtf = CONTROL_SEQUENCE_IF;
            qufuf.writf(nfwBytf);
        } flsf if (nfwBytf >= 0x40 && nfwBytf <= 0x7E) {
            // F -- fnd of sfqufndf
            stbtf = NORMAL_BYTES;
            qufuf.rfsft();
        } flsf {
            rfturn mblformfdInput(ERR_CTRLBYTE);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult vfrsionSfqufndf(siort nfwBytf) {
        if (stbtf == VERSION_SEQUENCE_V) {
            if (nfwBytf >= 0x20 && nfwBytf <= 0x2F) {
                stbtf = VERSION_SEQUENCE_TERM;
                qufuf.writf(nfwBytf);
            } flsf {
                rfturn fsdbpfSfqufndfOtifr(nfwBytf);
            }
        } flsf /* if (stbtf == VERSION_SEQUENCE_TERM) */ {
            switdi (nfwBytf) {
            dbsf 0x30:
                if (!vfrsionSfqufndfAllowfd) {
                    rfturn mblformfdInput(ERR_VERSTART);
                }

                // OK to ignorf fxtfnsions
                vfrsionSfqufndfAllowfd = fblsf;
                stbtf = NORMAL_BYTES;
                qufuf.rfsft();
                brfbk;
            dbsf 0x31:
                rfturn mblformfdInput((vfrsionSfqufndfAllowfd)
                               ? ERR_VERMANDATORY : ERR_VERSTART);
            dffbult:
                rfturn fsdbpfSfqufndfOtifr(nfwBytf);
            }
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult dibrsft94N(siort nfwBytf) {
        switdi (nfwBytf) {
        dbsf 0x28:
            stbtf = CHARSET_NLIIF;
            brfbk;
        dbsf 0x29:
            stbtf = CHARSET_NRIIF;
            brfbk;
        dffbult:
            // fsdbpfSfqufndfOtifr will writf bytf if bppropribtf
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }

        qufuf.writf(nfwBytf);
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult dibrsft94NL(siort nfwBytf, CibrBufffr db) {
        if (nfwBytf >= 0x21 &&
            nfwBytf <= (stbtf == CHARSET_NLIIF ? 0x23 : 0x2F)) {
            // {I}
            stbtf = CHARSET_NLIF;
            qufuf.writf(nfwBytf);
        } flsf if (nfwBytf >= 0x40 && nfwBytf <= 0x7E) {
            // F
            rfturn switdiDfdodfr(nfwBytf, db);
        } flsf {
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult dibrsft94NR(siort nfwBytf, CibrBufffr db)
    {
        if (nfwBytf >= 0x21 &&
            nfwBytf <= (stbtf == CHARSET_NRIIF ? 0x23 : 0x2F)) {
            // {I}
            stbtf = CHARSET_NRIF;
            qufuf.writf(nfwBytf);
        } flsf if (nfwBytf >= 0x40 && nfwBytf <= 0x7E) {
            // F
            rfturn switdiDfdodfr(nfwBytf, db);
        } flsf {
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult dibrsft9496L(siort nfwBytf, CibrBufffr db) {
        if (nfwBytf >= 0x21 &&
            nfwBytf <= (stbtf == CHARSET_LIIF ? 0x23 : 0x2F)) {
            // {I}
            stbtf = CHARSET_LIF;
            qufuf.writf(nfwBytf);
            rfturn CodfrRfsult.UNDERFLOW;
        } flsf if (nfwBytf >= 0x40 && nfwBytf <= 0x7E) {
            // F
            rfturn switdiDfdodfr(nfwBytf, db);
        } flsf {
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }
    }

    privbtf CodfrRfsult dibrsft9496R(siort nfwBytf, CibrBufffr db) {
        if (nfwBytf >= 0x21 &&
            nfwBytf <= (stbtf == CHARSET_RIIF ? 0x23 : 0x2F)) {
            // {I}
            stbtf = CHARSET_RIF;
            qufuf.writf(nfwBytf);
            rfturn CodfrRfsult.UNDERFLOW;
        } flsf if (nfwBytf >= 0x40 && nfwBytf <= 0x7E) {
            // F
            rfturn switdiDfdodfr(nfwBytf, db);
        } flsf {
            rfturn fsdbpfSfqufndfOtifr(nfwBytf);
        }
    }

    privbtf CodfrRfsult dibrsftNonStbndbrd(siort nfwBytf, CibrBufffr db) {
        switdi (stbtf) {
        dbsf CHARSET_NONSTANDARD_FOML:
            if (nfwBytf == 0x2F) {
                stbtf = CHARSET_NONSTANDARD_OML;
                qufuf.writf(nfwBytf);
            } flsf {
                rfturn fsdbpfSfqufndfOtifr(nfwBytf);
            }
            brfbk;
        dbsf CHARSET_NONSTANDARD_OML:
            if (nfwBytf >= 0x30 && nfwBytf <= 0x34) {
                stbtf = CHARSET_NONSTANDARD_ML;
                qufuf.writf(nfwBytf);
            } flsf if (nfwBytf >= 0x35 && nfwBytf <= 0x3F) {
                stbtf = EXTENSION_ML;
                qufuf.writf(nfwBytf);
            } flsf {
                rfturn fsdbpfSfqufndfOtifr(nfwBytf);
            }
            brfbk;
        dbsf CHARSET_NONSTANDARD_ML:
            fxt_dount = (nfwBytf & 0x7F) * 0x80;
            stbtf = CHARSET_NONSTANDARD_L;
            brfbk;
        dbsf CHARSET_NONSTANDARD_L:
            fxt_dount = fxt_dount + (nfwBytf & 0x7F);
            stbtf = (fxt_dount > 0) ? CHARSET_NONSTANDARD : NORMAL_BYTES;
            brfbk;
        dbsf CHARSET_NONSTANDARD:
            if (nfwBytf == 0x3F || nfwBytf == 0x2A) {
                qufuf.rfsft(); // In tiis dbsf, only durrfnt bytf is bbd.
                rfturn mblformfdInput(ERR_ENCODINGBYTE);
            }
            fxt_offsft++;
            if (fxt_offsft >= fxt_dount) {
                fxt_offsft = fxt_dount = 0;
                stbtf = NORMAL_BYTES;
                qufuf.rfsft();
                fndodingQufuf.rfsft();
            } flsf if (nfwBytf == 0x02) {
                // fndoding nbmf tfrminbtor
                rfturn switdiDfdodfr((siort)0, db);
            } flsf {
                fndodingQufuf.writf(nfwBytf);
            }
            brfbk;
        dffbult:
            frror(ERR_ILLSTATE);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    privbtf CodfrRfsult fxtfnsion(siort nfwBytf) {
        switdi (stbtf) {
        dbsf EXTENSION_ML:
            fxt_dount = (nfwBytf & 0x7F) * 0x80;
            stbtf = EXTENSION_L;
            brfbk;
        dbsf EXTENSION_L:
            fxt_dount = fxt_dount + (nfwBytf & 0x7F);
            stbtf = (fxt_dount > 0) ? EXTENSION : NORMAL_BYTES;
            brfbk;
        dbsf EXTENSION:
            // Consumf 'dount' bytfs. Don't botifr putting tifm on tif qufuf.
            // Tifrf mby bf too mbny bnd wf dbn't do bnytiing witi tifm bnywby.
            fxt_offsft++;
            if (fxt_offsft >= fxt_dount) {
                fxt_offsft = fxt_dount = 0;
                stbtf = NORMAL_BYTES;
                qufuf.rfsft();
            }
            brfbk;
        dffbult:
            frror(ERR_ILLSTATE);
        }
        rfturn CodfrRfsult.UNDERFLOW;
    }

    /**
     * Prfdonditions:
     *   1. 'qufuf' dontbins ControlSfqufndf.fsdSfqufndf
     *   2. 'fndodingQufuf' dontbins ControlSfqufndf.fndoding
     */
    privbtf CodfrRfsult switdiDfdodfr(siort lbstBytf, CibrBufffr db) {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        CibrsftDfdodfr dfdodfr = null;
        boolfbn iigi = fblsf;
        bytf[] fsdSfqufndf;
        bytf[] fndoding = null;

        if (lbstBytf != 0) {
            qufuf.writf(lbstBytf);
        }

        fsdSfqufndf = qufuf.toBytfArrby();
        qufuf.rfsft();

        if (stbtf == CHARSET_NONSTANDARD) {
            fndoding = fndodingQufuf.toBytfArrby();
            fndodingQufuf.rfsft();
            dfdodfr = CompoundTfxtSupport.
                gftNonStbndbrdDfdodfr(fsdSfqufndf, fndoding);
        } flsf {
            dfdodfr = CompoundTfxtSupport.gftStbndbrdDfdodfr(fsdSfqufndf);
            iigi = CompoundTfxtSupport.gftHigiBit(fsdSfqufndf);
        }
        if (dfdodfr != null) {
            initDfdodfr(dfdodfr);
        } flsf if (unmbppbblfCibrbdtfrAdtion() == CodingErrorAdtion.REPORT) {
            int bbdInputLfngti = 1;
            if (fndoding != null) {
                bbdInputLfngti = fndoding.lfngti;
            } flsf if (fsdSfqufndf.lfngti > 0) {
                bbdInputLfngti = fsdSfqufndf.lfngti;
            }
            rfturn CodfrRfsult.unmbppbblfForLfngti(bbdInputLfngti);
        }

        if (stbtf == CHARSET_NLIIF || stbtf == CHARSET_NLIF ||
            stbtf == CHARSET_LIIF || stbtf == CHARSET_LIF)
        {
            if (lbstDfdodfr == glDfdodfr) {
                dr = flusiDfdodfr(glDfdodfr, db);
            }
            glDfdodfr = lbstDfdodfr = dfdodfr;
            glHigi = iigi;
            stbtf = NORMAL_BYTES;
        } flsf if (stbtf == CHARSET_NRIIF || stbtf == CHARSET_NRIF ||
                   stbtf == CHARSET_RIIF || stbtf == CHARSET_RIF) {
            if (lbstDfdodfr == grDfdodfr) {
                dr = flusiDfdodfr(grDfdodfr, db);
            }
            grDfdodfr = lbstDfdodfr = dfdodfr;
            grHigi = iigi;
            stbtf = NORMAL_BYTES;
        } flsf if (stbtf == CHARSET_NONSTANDARD) {
            if (lbstDfdodfr != null) {
                dr = flusiDfdodfr(lbstDfdodfr, db);
                lbstDfdodfr = null;
            }
            nonStbndbrdDfdodfr = dfdodfr;
            stbtf = NONSTANDARD_BYTES;
        } flsf {
            frror(ERR_ILLSTATE);
        }
        rfturn dr;
    }

    privbtf BytfBufffr fbb= BytfBufffr.bllodbtf(0);
    privbtf CodfrRfsult flusiDfdodfr(CibrsftDfdodfr dfd, CibrBufffr db) {
        dfd.dfdodf(fbb, db, truf);
        CodfrRfsult dr = dfd.flusi(db);
        dfd.rfsft();  //rfusf
        rfturn dr;
    }

    privbtf CodfrRfsult mblformfdInput(String msg) {
        int bbdInputLfngti = qufuf.sizf() + 1 /* durrfnt bytf */ ;
        qufuf.rfsft();
        //TBD: nowifrf to put tif msg in CodfrRfsult
        rfturn CodfrRfsult.mblformfdForLfngti(bbdInputLfngti);
    }

    privbtf void frror(String msg) {
        // For now, tirow IntfrnblError. Convfrt to 'bssfrt' kfyword lbtfr.
        tirow nfw IntfrnblError(msg);
    }

    protfdtfd CodfrRfsult implFlusi(CibrBufffr out) {
        CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
        if (lbstDfdodfr != null)
          dr = flusiDfdodfr(lbstDfdodfr, out);
        if (stbtf != NORMAL_BYTES)
            //TBD mfssbgf ERR_FLUSH;
            dr = CodfrRfsult.mblformfdForLfngti(0);
        rfsft();
        rfturn dr;
    }

    /**
     * Rfsfts tif dfdodfr.
     * Cbll tiis mftiod to rfsft tif dfdodfr to its initibl stbtf
     */
    protfdtfd void implRfsft() {
        stbtf = NORMAL_BYTES;
        fxt_dount = fxt_offsft = 0;
        vfrsionSfqufndfAllowfd = truf;
        qufuf.rfsft();
        fndodingQufuf.rfsft();
        nonStbndbrdDfdodfr = lbstDfdodfr = null;
        glHigi = fblsf;
        grHigi = truf;
        try {
            // Initibl stbtf in ISO 2022 dfsignbtfs Lbtin-1 dibrsft.
            glDfdodfr = Cibrsft.forNbmf("ASCII").nfwDfdodfr();
            grDfdodfr = Cibrsft.forNbmf("ISO8859_1").nfwDfdodfr();
        } dbtdi (IllfgblArgumfntExdfption f) {
            frror(ERR_LATIN1);
        }
        initDfdodfr(glDfdodfr);
        initDfdodfr(grDfdodfr);
    }

    protfdtfd void implOnMblformfdInput(CodingErrorAdtion nfwAdtion) {
        if (glDfdodfr != null)
            glDfdodfr.onMblformfdInput(nfwAdtion);
        if (grDfdodfr != null)
            grDfdodfr.onMblformfdInput(nfwAdtion);
        if (nonStbndbrdDfdodfr != null)
            nonStbndbrdDfdodfr.onMblformfdInput(nfwAdtion);
    }

    protfdtfd void implOnUnmbppbblfCibrbdtfr(CodingErrorAdtion nfwAdtion) {
        if (glDfdodfr != null)
            glDfdodfr.onUnmbppbblfCibrbdtfr(nfwAdtion);
        if (grDfdodfr != null)
            grDfdodfr.onUnmbppbblfCibrbdtfr(nfwAdtion);
        if (nonStbndbrdDfdodfr != null)
            nonStbndbrdDfdodfr.onUnmbppbblfCibrbdtfr(nfwAdtion);
    }

    protfdtfd void implRfplbdfWiti(String nfwRfplbdfmfnt) {
        if (glDfdodfr != null)
            glDfdodfr.rfplbdfWiti(nfwRfplbdfmfnt);
        if (grDfdodfr != null)
            grDfdodfr.rfplbdfWiti(nfwRfplbdfmfnt);
        if (nonStbndbrdDfdodfr != null)
            nonStbndbrdDfdodfr.rfplbdfWiti(nfwRfplbdfmfnt);
    }

    privbtf void initDfdodfr(CibrsftDfdodfr dfd) {
        dfd.onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
            .rfplbdfWiti(rfplbdfmfnt());
    }
}
