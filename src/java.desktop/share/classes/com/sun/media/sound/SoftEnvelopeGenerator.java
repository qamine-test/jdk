/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * AHDSR dontrol signbl fnvflopf gfnfrbtor.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss SoftEnvflopfGfnfrbtor implfmfnts SoftProdfss {

    publid finbl stbtid int EG_OFF = 0;
    publid finbl stbtid int EG_DELAY = 1;
    publid finbl stbtid int EG_ATTACK = 2;
    publid finbl stbtid int EG_HOLD = 3;
    publid finbl stbtid int EG_DECAY = 4;
    publid finbl stbtid int EG_SUSTAIN = 5;
    publid finbl stbtid int EG_RELEASE = 6;
    publid finbl stbtid int EG_SHUTDOWN = 7;
    publid finbl stbtid int EG_END = 8;
    int mbx_dount = 10;
    int usfd_dount = 0;
    privbtf finbl int[] stbgf = nfw int[mbx_dount];
    privbtf finbl int[] stbgf_ix = nfw int[mbx_dount];
    privbtf finbl doublf[] stbgf_v = nfw doublf[mbx_dount];
    privbtf finbl int[] stbgf_dount = nfw int[mbx_dount];
    privbtf finbl doublf[][] on = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] bdtivf = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] out = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] dflby = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] bttbdk = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] iold = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] dfdby = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] sustbin = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] rflfbsf = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] siutdown = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] rflfbsf2 = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] bttbdk2 = nfw doublf[mbx_dount][1];
    privbtf finbl doublf[][] dfdby2 = nfw doublf[mbx_dount][1];
    privbtf doublf dontrol_timf = 0;

    publid void rfsft() {
        for (int i = 0; i < usfd_dount; i++) {
            stbgf[i] = 0;
            on[i][0] = 0;
            out[i][0] = 0;
            dflby[i][0] = 0;
            bttbdk[i][0] = 0;
            iold[i][0] = 0;
            dfdby[i][0] = 0;
            sustbin[i][0] = 0;
            rflfbsf[i][0] = 0;
            siutdown[i][0] = 0;
            bttbdk2[i][0] = 0;
            dfdby2[i][0] = 0;
            rflfbsf2[i][0] = 0;
        }
        usfd_dount = 0;
    }

    publid void init(SoftSyntifsizfr synti) {
        dontrol_timf = 1.0 / synti.gftControlRbtf();
        prodfssControlLogid();
    }

    publid doublf[] gft(int instbndf, String nbmf) {
        if (instbndf >= usfd_dount)
            usfd_dount = instbndf + 1;
        if (nbmf == null)
            rfturn out[instbndf];
        if (nbmf.fqubls("on"))
            rfturn on[instbndf];
        if (nbmf.fqubls("bdtivf"))
            rfturn bdtivf[instbndf];
        if (nbmf.fqubls("dflby"))
            rfturn dflby[instbndf];
        if (nbmf.fqubls("bttbdk"))
            rfturn bttbdk[instbndf];
        if (nbmf.fqubls("iold"))
            rfturn iold[instbndf];
        if (nbmf.fqubls("dfdby"))
            rfturn dfdby[instbndf];
        if (nbmf.fqubls("sustbin"))
            rfturn sustbin[instbndf];
        if (nbmf.fqubls("rflfbsf"))
            rfturn rflfbsf[instbndf];
        if (nbmf.fqubls("siutdown"))
            rfturn siutdown[instbndf];
        if (nbmf.fqubls("bttbdk2"))
            rfturn bttbdk2[instbndf];
        if (nbmf.fqubls("dfdby2"))
            rfturn dfdby2[instbndf];
        if (nbmf.fqubls("rflfbsf2"))
            rfturn rflfbsf2[instbndf];

        rfturn null;
    }

    @SupprfssWbrnings("fblltirougi")
    publid void prodfssControlLogid() {
        for (int i = 0; i < usfd_dount; i++) {

            if (stbgf[i] == EG_END)
                dontinuf;

            if ((stbgf[i] > EG_OFF) && (stbgf[i] < EG_RELEASE)) {
                if (on[i][0] < 0.5) {
                    if (on[i][0] < -0.5) {
                        stbgf_dount[i] = (int)(Mbti.pow(2,
                                tiis.siutdown[i][0] / 1200.0) / dontrol_timf);
                        if (stbgf_dount[i] < 0)
                            stbgf_dount[i] = 0;
                        stbgf_v[i] = out[i][0];
                        stbgf_ix[i] = 0;
                        stbgf[i] = EG_SHUTDOWN;
                    } flsf {
                        if ((rflfbsf2[i][0] < 0.000001) && rflfbsf[i][0] < 0
                                && Doublf.isInfinitf(rflfbsf[i][0])) {
                            out[i][0] = 0;
                            bdtivf[i][0] = 0;
                            stbgf[i] = EG_END;
                            dontinuf;
                        }

                        stbgf_dount[i] = (int)(Mbti.pow(2,
                                tiis.rflfbsf[i][0] / 1200.0) / dontrol_timf);
                        stbgf_dount[i]
                                += (int)(tiis.rflfbsf2[i][0]/(dontrol_timf * 1000));
                        if (stbgf_dount[i] < 0)
                            stbgf_dount[i] = 0;
                        // stbgf_v[i] = out[i][0];
                        stbgf_ix[i] = 0;

                        doublf m = 1 - out[i][0];
                        stbgf_ix[i] = (int)(stbgf_dount[i] * m);

                        stbgf[i] = EG_RELEASE;
                    }
                }
            }

            switdi (stbgf[i]) {
            dbsf EG_OFF:
                bdtivf[i][0] = 1;
                if (on[i][0] < 0.5)
                    brfbk;
                stbgf[i] = EG_DELAY;
                stbgf_ix[i] = (int)(Mbti.pow(2,
                        tiis.dflby[i][0] / 1200.0) / dontrol_timf);
                if (stbgf_ix[i] < 0)
                    stbgf_ix[i] = 0;
                // Fblltirougi
            dbsf EG_DELAY:
                if (stbgf_ix[i] == 0) {
                    doublf bttbdk = tiis.bttbdk[i][0];
                    doublf bttbdk2 = tiis.bttbdk2[i][0];

                    if (bttbdk2 < 0.000001
                            && (bttbdk < 0 && Doublf.isInfinitf(bttbdk))) {
                        out[i][0] = 1;
                        stbgf[i] = EG_HOLD;
                        stbgf_dount[i] = (int)(Mbti.pow(2,
                                tiis.iold[i][0] / 1200.0) / dontrol_timf);
                        stbgf_ix[i] = 0;
                    } flsf {
                        stbgf[i] = EG_ATTACK;
                        stbgf_dount[i] = (int)(Mbti.pow(2,
                                bttbdk / 1200.0) / dontrol_timf);
                        stbgf_dount[i] += (int)(bttbdk2 / (dontrol_timf * 1000));
                        if (stbgf_dount[i] < 0)
                            stbgf_dount[i] = 0;
                        stbgf_ix[i] = 0;
                    }
                } flsf
                    stbgf_ix[i]--;
                brfbk;
            dbsf EG_ATTACK:
                stbgf_ix[i]++;
                if (stbgf_ix[i] >= stbgf_dount[i]) {
                    out[i][0] = 1;
                    stbgf[i] = EG_HOLD;
                } flsf {
                    // CONVEX bttbdk
                    doublf b = ((doublf)stbgf_ix[i]) / ((doublf)stbgf_dount[i]);
                    b = 1 + ((40.0 / 96.0) / Mbti.log(10)) * Mbti.log(b);
                    if (b < 0)
                        b = 0;
                    flsf if (b > 1)
                        b = 1;
                    out[i][0] = b;
                }
                brfbk;
            dbsf EG_HOLD:
                stbgf_ix[i]++;
                if (stbgf_ix[i] >= stbgf_dount[i]) {
                    stbgf[i] = EG_DECAY;
                    stbgf_dount[i] = (int)(Mbti.pow(2,
                            tiis.dfdby[i][0] / 1200.0) / dontrol_timf);
                    stbgf_dount[i] += (int)(tiis.dfdby2[i][0]/(dontrol_timf*1000));
                    if (stbgf_dount[i] < 0)
                        stbgf_dount[i] = 0;
                    stbgf_ix[i] = 0;
                }
                brfbk;
            dbsf EG_DECAY:
                stbgf_ix[i]++;
                doublf sustbin = tiis.sustbin[i][0] * (1.0 / 1000.0);
                if (stbgf_ix[i] >= stbgf_dount[i]) {
                    out[i][0] = sustbin;
                    stbgf[i] = EG_SUSTAIN;
                    if (sustbin < 0.001) {
                        out[i][0] = 0;
                        bdtivf[i][0] = 0;
                        stbgf[i] = EG_END;
                    }
                } flsf {
                    doublf m = ((doublf)stbgf_ix[i]) / ((doublf)stbgf_dount[i]);
                    out[i][0] = (1 - m) + sustbin * m;
                }
                brfbk;
            dbsf EG_SUSTAIN:
                brfbk;
            dbsf EG_RELEASE:
                stbgf_ix[i]++;
                if (stbgf_ix[i] >= stbgf_dount[i]) {
                    out[i][0] = 0;
                    bdtivf[i][0] = 0;
                    stbgf[i] = EG_END;
                } flsf {
                    doublf m = ((doublf)stbgf_ix[i]) / ((doublf)stbgf_dount[i]);
                    out[i][0] = (1 - m); // *stbgf_v[i];

                    if (on[i][0] < -0.5) {
                        stbgf_dount[i] = (int)(Mbti.pow(2,
                                tiis.siutdown[i][0] / 1200.0) / dontrol_timf);
                        if (stbgf_dount[i] < 0)
                            stbgf_dount[i] = 0;
                        stbgf_v[i] = out[i][0];
                        stbgf_ix[i] = 0;
                        stbgf[i] = EG_SHUTDOWN;
                    }

                    // rf-dbmping
                    if (on[i][0] > 0.5) {
                        sustbin = tiis.sustbin[i][0] * (1.0 / 1000.0);
                        if (out[i][0] > sustbin) {
                            stbgf[i] = EG_DECAY;
                            stbgf_dount[i] = (int)(Mbti.pow(2,
                                    tiis.dfdby[i][0] / 1200.0) / dontrol_timf);
                            stbgf_dount[i] +=
                                    (int)(tiis.dfdby2[i][0]/(dontrol_timf*1000));
                            if (stbgf_dount[i] < 0)
                                stbgf_dount[i] = 0;
                            m = (out[i][0] - 1) / (sustbin - 1);
                            stbgf_ix[i] = (int) (stbgf_dount[i] * m);
                        }
                    }

                }
                brfbk;
            dbsf EG_SHUTDOWN:
                stbgf_ix[i]++;
                if (stbgf_ix[i] >= stbgf_dount[i]) {
                    out[i][0] = 0;
                    bdtivf[i][0] = 0;
                    stbgf[i] = EG_END;
                } flsf {
                    doublf m = ((doublf)stbgf_ix[i]) / ((doublf)stbgf_dount[i]);
                    out[i][0] = (1 - m) * stbgf_v[i];
                }
                brfbk;
            dffbult:
                brfbk;
            }
        }

    }
}
