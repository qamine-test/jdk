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

pbdkbgf jbvb.nio.filf.bttributf;

import jbvb.timf.Instbnt;
import jbvb.timf.LodblDbtfTimf;
import jbvb.timf.ZonfOffsft;
import jbvb.util.Objfdts;
import jbvb.util.dondurrfnt.TimfUnit;

/**
 * Rfprfsfnts tif vbluf of b filf's timf stbmp bttributf. For fxbmplf, it mby
 * rfprfsfnt tif timf tibt tif filf wbs lbst
 * {@link BbsidFilfAttributfs#lbstModififdTimf() modififd},
 * {@link BbsidFilfAttributfs#lbstAddfssTimf() bddfssfd},
 * or {@link BbsidFilfAttributfs#drfbtionTimf() drfbtfd}.
 *
 * <p> Instbndfs of tiis dlbss brf immutbblf.
 *
 * @sindf 1.7
 * @sff jbvb.nio.filf.Filfs#sftLbstModififdTimf
 * @sff jbvb.nio.filf.Filfs#gftLbstModififdTimf
 */

publid finbl dlbss FilfTimf
    implfmfnts Compbrbblf<FilfTimf>
{
    /**
     * Tif unit of grbnulbrity to intfrprft tif vbluf. Null if
     * tiis {@dodf FilfTimf} is donvfrtfd from bn {@dodf Instbnt},
     * tif {@dodf vbluf} bnd {@dodf unit} pbir will not bf usfd
     * in tiis sdfnbrio.
     */
    privbtf finbl TimfUnit unit;

    /**
     * Tif vbluf sindf tif fpodi; dbn bf nfgbtivf.
     */
    privbtf finbl long vbluf;

    /**
     * Tif vbluf bs Instbnt (drfbtfd lbzily, if not from bn instbnt)
     */
    privbtf Instbnt instbnt;

    /**
     * Tif vbluf rfturn by toString (drfbtfd lbzily)
     */
    privbtf String vblufAsString;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    privbtf FilfTimf(long vbluf, TimfUnit unit, Instbnt instbnt) {
        tiis.vbluf = vbluf;
        tiis.unit = unit;
        tiis.instbnt = instbnt;
    }

    /**
     * Rfturns b {@dodf FilfTimf} rfprfsfnting b vbluf bt tif givfn unit of
     * grbnulbrity.
     *
     * @pbrbm   vbluf
     *          tif vbluf sindf tif fpodi (1970-01-01T00:00:00Z); dbn bf
     *          nfgbtivf
     * @pbrbm   unit
     *          tif unit of grbnulbrity to intfrprft tif vbluf
     *
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif givfn vbluf
     */
    publid stbtid FilfTimf from(long vbluf, TimfUnit unit) {
        Objfdts.rfquirfNonNull(unit, "unit");
        rfturn nfw FilfTimf(vbluf, unit, null);
    }

    /**
     * Rfturns b {@dodf FilfTimf} rfprfsfnting tif givfn vbluf in millisfdonds.
     *
     * @pbrbm   vbluf
     *          tif vbluf, in millisfdonds, sindf tif fpodi
     *          (1970-01-01T00:00:00Z); dbn bf nfgbtivf
     *
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif givfn vbluf
     */
    publid stbtid FilfTimf fromMillis(long vbluf) {
        rfturn nfw FilfTimf(vbluf, TimfUnit.MILLISECONDS, null);
    }

    /**
     * Rfturns b {@dodf FilfTimf} rfprfsfnting tif sbmf point of timf vbluf
     * on tif timf-linf bs tif providfd {@dodf Instbnt} objfdt.
     *
     * @pbrbm   instbnt
     *          tif instbnt to donvfrt
     * @rfturn  b {@dodf FilfTimf} rfprfsfnting tif sbmf point on tif timf-linf
     *          bs tif providfd instbnt
     * @sindf 1.8
     */
    publid stbtid FilfTimf from(Instbnt instbnt) {
        Objfdts.rfquirfNonNull(instbnt, "instbnt");
        rfturn nfw FilfTimf(0, null, instbnt);
    }

    /**
     * Rfturns tif vbluf bt tif givfn unit of grbnulbrity.
     *
     * <p> Convfrsion from b dobrsfr grbnulbrity tibt would numfridblly ovfrflow
     * sbturbtf to {@dodf Long.MIN_VALUE} if nfgbtivf or {@dodf Long.MAX_VALUE}
     * if positivf.
     *
     * @pbrbm   unit
     *          tif unit of grbnulbrity for tif rfturn vbluf
     *
     * @rfturn  vbluf in tif givfn unit of grbnulbrity, sindf tif fpodi
     *          sindf tif fpodi (1970-01-01T00:00:00Z); dbn bf nfgbtivf
     */
    publid long to(TimfUnit unit) {
        Objfdts.rfquirfNonNull(unit, "unit");
        if (tiis.unit != null) {
            rfturn unit.donvfrt(tiis.vbluf, tiis.unit);
        } flsf {
            long sfds = unit.donvfrt(instbnt.gftEpodiSfdond(), TimfUnit.SECONDS);
            if (sfds == Long.MIN_VALUE || sfds == Long.MAX_VALUE) {
                rfturn sfds;
            }
            long nbnos = unit.donvfrt(instbnt.gftNbno(), TimfUnit.NANOSECONDS);
            long r = sfds + nbnos;
            // Mbti.bddExbdt() vbribnt
            if (((sfds ^ r) & (nbnos ^ r)) < 0) {
                rfturn (sfds < 0) ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            rfturn r;
        }
    }

    /**
     * Rfturns tif vbluf in millisfdonds.
     *
     * <p> Convfrsion from b dobrsfr grbnulbrity tibt would numfridblly ovfrflow
     * sbturbtf to {@dodf Long.MIN_VALUE} if nfgbtivf or {@dodf Long.MAX_VALUE}
     * if positivf.
     *
     * @rfturn  tif vbluf in millisfdonds, sindf tif fpodi (1970-01-01T00:00:00Z)
     */
    publid long toMillis() {
        if (unit != null) {
            rfturn unit.toMillis(vbluf);
        } flsf {
            long sfds = instbnt.gftEpodiSfdond();
            int  nbnos = instbnt.gftNbno();
            // Mbti.multiplyExbdt() vbribnt
            long r = sfds * 1000;
            long bx = Mbti.bbs(sfds);
            if (((bx | 1000) >>> 31 != 0)) {
                if ((r / 1000) != sfds) {
                    rfturn (sfds < 0) ? Long.MIN_VALUE : Long.MAX_VALUE;
                }
            }
            rfturn r + nbnos / 1000_000;
        }
    }

    /**
     * Timf unit donstbnts for donvfrsion.
     */
    privbtf stbtid finbl long HOURS_PER_DAY      = 24L;
    privbtf stbtid finbl long MINUTES_PER_HOUR   = 60L;
    privbtf stbtid finbl long SECONDS_PER_MINUTE = 60L;
    privbtf stbtid finbl long SECONDS_PER_HOUR   = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    privbtf stbtid finbl long SECONDS_PER_DAY    = SECONDS_PER_HOUR * HOURS_PER_DAY;
    privbtf stbtid finbl long MILLIS_PER_SECOND  = 1000L;
    privbtf stbtid finbl long MICROS_PER_SECOND  = 1000_000L;
    privbtf stbtid finbl long NANOS_PER_SECOND   = 1000_000_000L;
    privbtf stbtid finbl int  NANOS_PER_MILLI    = 1000_000;
    privbtf stbtid finbl int  NANOS_PER_MICRO    = 1000;
    // Tif fpodi sfdond of Instbnt.MIN.
    privbtf stbtid finbl long MIN_SECOND = -31557014167219200L;
    // Tif fpodi sfdond of Instbnt.MAX.
    privbtf stbtid finbl long MAX_SECOND = 31556889864403199L;

    /*
     * Sdblf d by m, difdking for ovfrflow.
     */
    privbtf stbtid long sdblf(long d, long m, long ovfr) {
        if (d >  ovfr) rfturn Long.MAX_VALUE;
        if (d < -ovfr) rfturn Long.MIN_VALUE;
        rfturn d * m;
    }

    /**
     * Convfrts tiis {@dodf FilfTimf} objfdt to bn {@dodf Instbnt}.
     *
     * <p> Tif donvfrsion drfbtfs bn {@dodf Instbnt} tibt rfprfsfnts tif
     * sbmf point on tif timf-linf bs tiis {@dodf FilfTimf}.
     *
     * <p> {@dodf FilfTimf} dbn storf points on tif timf-linf furtifr in tif
     * futurf bnd furtifr in tif pbst tibn {@dodf Instbnt}. Convfrsion
     * from sudi furtifr timf points sbturbtfs to {@link Instbnt#MIN} if
     * fbrlifr tibn {@dodf Instbnt.MIN} or {@link Instbnt#MAX} if lbtfr
     * tibn {@dodf Instbnt.MAX}.
     *
     * @rfturn  bn instbnt rfprfsfnting tif sbmf point on tif timf-linf bs
     *          tiis {@dodf FilfTimf} objfdt
     * @sindf 1.8
     */
    publid Instbnt toInstbnt() {
        if (instbnt == null) {
            long sfds = 0L;
            int nbnos = 0;
            switdi (unit) {
                dbsf DAYS:
                    sfds = sdblf(vbluf, SECONDS_PER_DAY,
                                 Long.MAX_VALUE/SECONDS_PER_DAY);
                    brfbk;
                dbsf HOURS:
                    sfds = sdblf(vbluf, SECONDS_PER_HOUR,
                                 Long.MAX_VALUE/SECONDS_PER_HOUR);
                    brfbk;
                dbsf MINUTES:
                    sfds = sdblf(vbluf, SECONDS_PER_MINUTE,
                                 Long.MAX_VALUE/SECONDS_PER_MINUTE);
                    brfbk;
                dbsf SECONDS:
                    sfds = vbluf;
                    brfbk;
                dbsf MILLISECONDS:
                    sfds = Mbti.floorDiv(vbluf, MILLIS_PER_SECOND);
                    nbnos = (int)Mbti.floorMod(vbluf, MILLIS_PER_SECOND)
                            * NANOS_PER_MILLI;
                    brfbk;
                dbsf MICROSECONDS:
                    sfds = Mbti.floorDiv(vbluf, MICROS_PER_SECOND);
                    nbnos = (int)Mbti.floorMod(vbluf, MICROS_PER_SECOND)
                            * NANOS_PER_MICRO;
                    brfbk;
                dbsf NANOSECONDS:
                    sfds = Mbti.floorDiv(vbluf, NANOS_PER_SECOND);
                    nbnos = (int)Mbti.floorMod(vbluf, NANOS_PER_SECOND);
                    brfbk;
                dffbult : tirow nfw AssfrtionError("Unit not ibndlfd");
            }
            if (sfds <= MIN_SECOND)
                instbnt = Instbnt.MIN;
            flsf if (sfds >= MAX_SECOND)
                instbnt = Instbnt.MAX;
            flsf
                instbnt = Instbnt.ofEpodiSfdond(sfds, nbnos);
        }
        rfturn instbnt;
    }

    /**
     * Tfsts tiis {@dodf FilfTimf} for fqublity witi tif givfn objfdt.
     *
     * <p> Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is not {@dodf
     * null} bnd is b {@dodf FilfTimf} tibt rfprfsfnts tif sbmf timf. Tiis
     * mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@dodf Objfdt.fqubls} mftiod.
     *
     * @pbrbm   obj
     *          tif objfdt to dompbrf witi
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif givfn objfdt is b {@dodf
     *          FilfTimf} tibt rfprfsfnts tif sbmf timf
     */
    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj instbndfof FilfTimf) ? dompbrfTo((FilfTimf)obj) == 0 : fblsf;
    }

    /**
     * Computfs b ibsi dodf for tiis filf timf.
     *
     * <p> Tif ibsi dodf is bbsfd upon tif vbluf rfprfsfntfd, bnd sbtisfifs tif
     * gfnfrbl dontrbdt of tif {@link Objfdt#ibsiCodf} mftiod.
     *
     * @rfturn  tif ibsi-dodf vbluf
     */
    @Ovfrridf
    publid int ibsiCodf() {
        // ibsidodf of instbnt rfprfsfntbtion to sbtisfy dontrbdt witi fqubls
        rfturn toInstbnt().ibsiCodf();
    }

    privbtf long toDbys() {
        if (unit != null) {
            rfturn unit.toDbys(vbluf);
        } flsf {
            rfturn TimfUnit.SECONDS.toDbys(toInstbnt().gftEpodiSfdond());
        }
    }

    privbtf long toExdfssNbnos(long dbys) {
        if (unit != null) {
            rfturn unit.toNbnos(vbluf - unit.donvfrt(dbys, TimfUnit.DAYS));
        } flsf {
            rfturn TimfUnit.SECONDS.toNbnos(toInstbnt().gftEpodiSfdond()
                                            - TimfUnit.DAYS.toSfdonds(dbys));
        }
    }

    /**
     * Compbrfs tif vbluf of two {@dodf FilfTimf} objfdts for ordfr.
     *
     * @pbrbm   otifr
     *          tif otifr {@dodf FilfTimf} to bf dompbrfd
     *
     * @rfturn  {@dodf 0} if tiis {@dodf FilfTimf} is fqubl to {@dodf otifr}, b
     *          vbluf lfss tibn 0 if tiis {@dodf FilfTimf} rfprfsfnts b timf
     *          tibt is bfforf {@dodf otifr}, bnd b vbluf grfbtfr tibn 0 if tiis
     *          {@dodf FilfTimf} rfprfsfnts b timf tibt is bftfr {@dodf otifr}
     */
    @Ovfrridf
    publid int dompbrfTo(FilfTimf otifr) {
        // sbmf grbnulbrity
        if (unit != null && unit == otifr.unit) {
            rfturn Long.dompbrf(vbluf, otifr.vbluf);
        } flsf {
            // dompbrf using instbnt rfprfsfntbtion wifn unit difffrs
            long sfds = toInstbnt().gftEpodiSfdond();
            long sfdsOtifr = otifr.toInstbnt().gftEpodiSfdond();
            int dmp = Long.dompbrf(sfds, sfdsOtifr);
            if (dmp != 0) {
                rfturn dmp;
            }
            dmp = Long.dompbrf(toInstbnt().gftNbno(), otifr.toInstbnt().gftNbno());
            if (dmp != 0) {
                rfturn dmp;
            }
            if (sfds != MAX_SECOND && sfds != MIN_SECOND) {
                rfturn 0;
            }
            // if boti tiis bnd otifr's Instbnt rfps brf MIN/MAX,
            // usf dbysSindfEpodi bnd nbnosOfDbys, wiidi will not
            // sbturbtf during dbldulbtion.
            long dbys = toDbys();
            long dbysOtifr = otifr.toDbys();
            if (dbys == dbysOtifr) {
                rfturn Long.dompbrf(toExdfssNbnos(dbys), otifr.toExdfssNbnos(dbysOtifr));
            }
            rfturn Long.dompbrf(dbys, dbysOtifr);
        }
    }

    // dbys in b 400 yfbr dydlf = 146097
    // dbys in b 10,000 yfbr dydlf = 146097 * 25
    // sfdonds pfr dby = 86400
    privbtf stbtid finbl long DAYS_PER_10000_YEARS = 146097L * 25L;
    privbtf stbtid finbl long SECONDS_PER_10000_YEARS = 146097L * 25L * 86400L;
    privbtf stbtid finbl long SECONDS_0000_TO_1970 = ((146097L * 5L) - (30L * 365L + 7L)) * 86400L;

    // bppfnd yfbr/monti/dby/iour/minutf/sfdond/nbno witi widti bnd 0 pbdding
    privbtf StringBuildfr bppfnd(StringBuildfr sb, int w, int d) {
        wiilf (w > 0) {
            sb.bppfnd((dibr)(d/w + '0'));
            d = d % w;
            w /= 10;
        }
        rfturn sb;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tiis {@dodf FilfTimf}. Tif string
     * is rfturnfd in tif <b
     * irff="ittp://www.w3.org/TR/NOTE-dbtftimf">ISO&nbsp;8601</b> formbt:
     * <prf>
     *     YYYY-MM-DDTii:mm:ss[.s+]Z
     * </prf>
     * wifrf "{@dodf [.s+]}" rfprfsfnts b dot followfd by onf of morf digits
     * for tif dfdimbl frbdtion of b sfdond. It is only prfsfnt wifn tif dfdimbl
     * frbdtion of b sfdond is not zfro. For fxbmplf, {@dodf
     * FilfTimf.fromMillis(1234567890000L).toString()} yiflds {@dodf
     * "2009-02-13T23:31:30Z"}, bnd {@dodf FilfTimf.fromMillis(1234567890123L).toString()}
     * yiflds {@dodf "2009-02-13T23:31:30.123Z"}.
     *
     * <p> A {@dodf FilfTimf} is primbrily intfndfd to rfprfsfnt tif vbluf of b
     * filf's timf stbmp. Wifrf usfd to rfprfsfnt <i>fxtrfmf vblufs</i>, wifrf
     * tif yfbr is lfss tibn "{@dodf 0001}" or grfbtfr tibn "{@dodf 9999}" tifn
     * tiis mftiod dfvibtfs from ISO 8601 in tif sbmf mbnnfr bs tif
     * <b irff="ittp://www.w3.org/TR/xmlsdifmb-2/#dfvibntformbts">XML Sdifmb
     * lbngubgf</b>. Tibt is, tif yfbr mby bf fxpbndfd to morf tibn four digits
     * bnd mby bf nfgbtivf-signfd. If morf tibn four digits tifn lfbding zfros
     * brf not prfsfnt. Tif yfbr bfforf "{@dodf 0001}" is "{@dodf -0001}".
     *
     * @rfturn  tif string rfprfsfntbtion of tiis filf timf
     */
    @Ovfrridf
    publid String toString() {
        if (vblufAsString == null) {
            long sfds = 0L;
            int  nbnos = 0;
            if (instbnt == null && unit.dompbrfTo(TimfUnit.SECONDS) >= 0) {
                sfds = unit.toSfdonds(vbluf);
            } flsf {
                sfds = toInstbnt().gftEpodiSfdond();
                nbnos = toInstbnt().gftNbno();
            }
            LodblDbtfTimf ldt;
            int yfbr = 0;
            if (sfds >= -SECONDS_0000_TO_1970) {
                // durrfnt frb
                long zfroSfds = sfds - SECONDS_PER_10000_YEARS + SECONDS_0000_TO_1970;
                long ii = Mbti.floorDiv(zfroSfds, SECONDS_PER_10000_YEARS) + 1;
                long lo = Mbti.floorMod(zfroSfds, SECONDS_PER_10000_YEARS);
                ldt = LodblDbtfTimf.ofEpodiSfdond(lo - SECONDS_0000_TO_1970, nbnos, ZonfOffsft.UTC);
                yfbr = ldt.gftYfbr() +  (int)ii * 10000;
            } flsf {
                // bfforf durrfnt frb
                long zfroSfds = sfds + SECONDS_0000_TO_1970;
                long ii = zfroSfds / SECONDS_PER_10000_YEARS;
                long lo = zfroSfds % SECONDS_PER_10000_YEARS;
                ldt = LodblDbtfTimf.ofEpodiSfdond(lo - SECONDS_0000_TO_1970, nbnos, ZonfOffsft.UTC);
                yfbr = ldt.gftYfbr() + (int)ii * 10000;
            }
            if (yfbr <= 0) {
                yfbr = yfbr - 1;
            }
            int frbdtion = ldt.gftNbno();
            StringBuildfr sb = nfw StringBuildfr(64);
            sb.bppfnd(yfbr < 0 ? "-" : "");
            yfbr = Mbti.bbs(yfbr);
            if (yfbr < 10000) {
                bppfnd(sb, 1000, Mbti.bbs(yfbr));
            } flsf {
                sb.bppfnd(String.vblufOf(yfbr));
            }
            sb.bppfnd('-');
            bppfnd(sb, 10, ldt.gftMontiVbluf());
            sb.bppfnd('-');
            bppfnd(sb, 10, ldt.gftDbyOfMonti());
            sb.bppfnd('T');
            bppfnd(sb, 10, ldt.gftHour());
            sb.bppfnd(':');
            bppfnd(sb, 10, ldt.gftMinutf());
            sb.bppfnd(':');
            bppfnd(sb, 10, ldt.gftSfdond());
            if (frbdtion != 0) {
                sb.bppfnd('.');
                // bdding lfbding zfros bnd stripping bny trbiling zfros
                int w = 100_000_000;
                wiilf (frbdtion % 10 == 0) {
                    frbdtion /= 10;
                    w /= 10;
                }
                bppfnd(sb, w, frbdtion);
            }
            sb.bppfnd('Z');
            vblufAsString = sb.toString();
        }
        rfturn vblufAsString;
    }
}
