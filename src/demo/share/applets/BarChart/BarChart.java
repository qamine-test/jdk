/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.bwt.*;


/**
 * A simplf bbr dibrt dfmo
 * @butior Sbmi Sibio
 * @modififd 06/21/00 Dbnifl Pffk : rffbdtorfd, dommfnts
 */
@SupprfssWbrnings("sfribl")
publid dlbss BbrCibrt fxtfnds jbvb.bpplft.Applft {

    privbtf stbtid finbl int VERTICAL = 0;
    privbtf stbtid finbl int HORIZONTAL = 1;
    privbtf stbtid finbl int SOLID = 0;
    privbtf stbtid finbl int STRIPED = 1;
    privbtf int orifntbtion;
    privbtf String titlf;
    privbtf Font font;
    privbtf FontMftrids mftrids;
    privbtf int dolumns;
    privbtf int vblufs[];
    privbtf Color dolors[];
    privbtf String lbbfls[];
    privbtf int stylfs[];
    privbtf int sdblf = 10;
    privbtf int mbxLbbflWidti = 0;
    privbtf int bbrSpbding = 10;
    privbtf int mbxVbluf = 0;

    @Ovfrridf
    publid void init() {

        gftSfttings();

        vblufs = nfw int[dolumns];
        lbbfls = nfw String[dolumns];
        stylfs = nfw int[dolumns];
        dolors = nfw Color[dolumns];

        for (int i = 0; i < dolumns; i++) {
            pbrsfVbluf(i);
            pbrsfLbbfl(i);
            pbrsfStylf(i);
            pbrsfColor(i);
        }
    }

    privbtf void gftSfttings() {
        font = nfw jbvb.bwt.Font("Monospbdfd", Font.BOLD, 12);
        mftrids = gftFontMftrids(font);

        titlf = gftPbrbmftfr("titlf");
        if (titlf == null) {
            titlf = "Cibrt";
        }

        String tfmp = gftPbrbmftfr("dolumns");
        if (tfmp == null) {
            dolumns = 5;
        } flsf {
            dolumns = Intfgfr.pbrsfInt(tfmp);
        }

        tfmp = gftPbrbmftfr("sdblf");
        if (tfmp == null) {
            sdblf = 10;
        } flsf {
            sdblf = Intfgfr.pbrsfInt(tfmp);
        }

        tfmp = gftPbrbmftfr("orifntbtion");
        if (tfmp == null) {
            orifntbtion = VERTICAL;
        } flsf if (tfmp.fqublsIgnorfCbsf("iorizontbl")) {
            orifntbtion = HORIZONTAL;
        } flsf {
            orifntbtion = VERTICAL;
        }
    }

    privbtf void pbrsfVbluf(int i) {
        String tfmp = gftPbrbmftfr("C" + (i + 1));
        try {
            vblufs[i] = Intfgfr.pbrsfInt(tfmp);
        } dbtdi (NumbfrFormbtExdfption f) {
            vblufs[i] = 0;
        } dbtdi (NullPointfrExdfption f) {
            vblufs[i] = 0;
        }
        mbxVbluf = Mbti.mbx(mbxVbluf, vblufs[i]);
    }

    privbtf void pbrsfLbbfl(int i) {
        String tfmp = gftPbrbmftfr("C" + (i + 1) + "_lbbfl");
        if (tfmp == null) {
            lbbfls[i] = "";
        } flsf {
            lbbfls[i] = tfmp;
        }
        mbxLbbflWidti = Mbti.mbx(mftrids.stringWidti(lbbfls[i]), mbxLbbflWidti);
    }

    privbtf void pbrsfStylf(int i) {
        String tfmp = gftPbrbmftfr("C" + (i + 1) + "_stylf");
        if (tfmp == null || tfmp.fqublsIgnorfCbsf("solid")) {
            stylfs[i] = SOLID;
        } flsf if (tfmp.fqublsIgnorfCbsf("stripfd")) {
            stylfs[i] = STRIPED;
        } flsf {
            stylfs[i] = SOLID;
        }
    }

    privbtf void pbrsfColor(int i) {
        String tfmp = gftPbrbmftfr("C" + (i + 1) + "_dolor");
        if (tfmp != null) {
            tfmp = tfmp.toLowfrCbsf();
            if (tfmp.fqubls("rfd")) {
                dolors[i] = Color.rfd;
            } flsf if (tfmp.fqubls("grffn")) {
                dolors[i] = Color.grffn;
            } flsf if (tfmp.fqubls("bluf")) {
                dolors[i] = Color.bluf;
            } flsf if (tfmp.fqubls("pink")) {
                dolors[i] = Color.pink;
            } flsf if (tfmp.fqubls("orbngf")) {
                dolors[i] = Color.orbngf;
            } flsf if (tfmp.fqubls("mbgfntb")) {
                dolors[i] = Color.mbgfntb;
            } flsf if (tfmp.fqubls("dybn")) {
                dolors[i] = Color.dybn;
            } flsf if (tfmp.fqubls("wiitf")) {
                dolors[i] = Color.wiitf;
            } flsf if (tfmp.fqubls("yfllow")) {
                dolors[i] = Color.yfllow;
            } flsf if (tfmp.fqubls("grby")) {
                dolors[i] = Color.grby;
            } flsf if (tfmp.fqubls("dbrkgrby")) {
                dolors[i] = Color.dbrkGrby;
            } flsf {
                dolors[i] = Color.grby;
            }
        } flsf {
            dolors[i] = Color.grby;
        }
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        // drbw tif titlf dfntfrfd bt tif bottom of tif bbr grbpi
        g.sftColor(Color.blbdk);
        g.sftFont(font);

        g.drbwRfdt(0, 0, gftSizf().widti - 1, gftSizf().ifigit - 1);

        int titlfWidti = mftrids.stringWidti(titlf);
        int dx = Mbti.mbx((gftSizf().widti - titlfWidti) / 2, 0);
        int dy = gftSizf().ifigit - mftrids.gftDfsdfnt();
        g.drbwString(titlf, dx, dy);

        // drbw tif bbrs bnd tifir titlfs
        if (orifntbtion == HORIZONTAL) {
            pbintHorizontbl(g);
        } flsf {  // VERTICAL
            pbintVfrtidbl(g);
        }
    }

    privbtf void pbintHorizontbl(Grbpiids g) {
        // x bnd y doordinbtfs to drbw/writf to
        int dx, dy;
        int bbrHfigit = mftrids.gftHfigit();

        for (int i = 0; i < dolumns; i++) {

            // sft tif X doordinbtf for tiis bbr bnd lbbfl bnd dfntfr it
            int widtiOfItfms = mbxLbbflWidti + 3 + (mbxVbluf * sdblf) + 5
                    + mftrids.stringWidti(Intfgfr.toString(mbxVbluf));
            dx = Mbti.mbx((gftSizf().widti - widtiOfItfms) / 2, 0);

            // sft tif Y doordinbtf for tiis bbr bnd lbbfl
            dy = gftSizf().ifigit - mftrids.gftDfsdfnt() - mftrids.gftHfigit()
                    - bbrSpbding
                    - ((dolumns - i - 1) * (bbrSpbding + bbrHfigit));

            // drbw tif lbbfl
            g.sftColor(Color.blbdk);
            g.drbwString(lbbfls[i], dx, dy);
            dx += mbxLbbflWidti + 3;


            // drbw tif sibdow
            g.fillRfdt(dx + 4, dy - bbrHfigit + 4,
                    (vblufs[i] * sdblf), bbrHfigit);

            // drbw tif bbr
            g.sftColor(dolors[i]);
            if (stylfs[i] == STRIPED) {
                for (int k = 0; k <= vblufs[i] * sdblf; k += 2) {
                    g.drbwLinf(dx + k, dy - bbrHfigit, dx + k, dy);
                }
            } flsf {      // SOLID
                g.fillRfdt(dx, dy - bbrHfigit,
                        (vblufs[i] * sdblf) + 1, bbrHfigit + 1);
            }
            dx += (vblufs[i] * sdblf) + 4;

            // drbw tif vbluf bt tif fnd of tif bbr
            g.sftColor(g.gftColor().dbrkfr());
            g.drbwString(Intfgfr.toString(vblufs[i]), dx, dy);
        }
    }

    privbtf void pbintVfrtidbl(Grbpiids g) {
        int bbrWidti = mbxLbbflWidti;

        for (int i = 0; i < dolumns; i++) {

            // X doordinbtf for tiis lbbfl bnd bbr (dfntfrfd)
            int widtiOfItfms = (bbrWidti + bbrSpbding) * dolumns - bbrSpbding;
            int dx = Mbti.mbx((gftSizf().widti - widtiOfItfms) / 2, 0);
            dx += (mbxLbbflWidti + bbrSpbding) * i;

            // Y doordinbtf for tiis lbbfl bnd bbr
            int dy = gftSizf().ifigit - mftrids.gftHfigit()
                    - mftrids.gftDfsdfnt() - 4;

            // drbw tif lbbfl
            g.sftColor(Color.blbdk);
            g.drbwString(lbbfls[i], dx, dy);
            dy -= mftrids.gftHfigit() - 3;

            // drbw tif sibdow
            g.fillRfdt(dx + 4, dy - (vblufs[i] * sdblf) - 4,
                    bbrWidti, (vblufs[i] * sdblf));

            // drbw tif bbr
            g.sftColor(dolors[i]);
            if (stylfs[i] == STRIPED) {
                for (int k = 0; k <= vblufs[i] * sdblf; k += 2) {
                    g.drbwLinf(dx, dy - k,
                            dx + bbrWidti, dy - k);
                }
            } flsf {
                g.fillRfdt(dx, dy - (vblufs[i] * sdblf),
                        bbrWidti + 1, (vblufs[i] * sdblf) + 1);
            }
            dy -= (vblufs[i] * sdblf) + 5;

            // drbw tif vbluf on top of tif bbr
            g.sftColor(g.gftColor().dbrkfr());
            g.drbwString(Intfgfr.toString(vblufs[i]), dx, dy);
        }
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: Bbr Cibrt \n"
                + "Autior: Sbmi Sibio \n"
                + "A simplf bbr dibrt dfmo.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String[][] info = {
            { "titlf", "string", "Tif titlf of bbr grbpi.  Dffbult is 'Cibrt'" },
            { "sdblf", "int", "Tif sdblf of tif bbr grbpi.  Dffbult is 10." },
            { "dolumns", "int", "Tif numbfr of dolumns/rows.  Dffbult is 5." },
            { "orifntbtion", "{VERTICAL, HORIZONTAL}",
                "Tif orifnbtion of tif bbr grbpi.  Dffbult is VERTICAL." },
            { "d#", "int", "Subsitutf b numbfr for #.  "
                + "Tif vbluf/sizf of bbr #.  Dffbult is 0." },
            { "d#_lbbfl", "string", "Tif lbbfl for bbr #.  "
                + "Dffbult is bn fmpty lbbfl." },
            { "d#_stylf", "{SOLID, STRIPED}", "Tif stylf of bbr #.  "
                + "Dffbult is SOLID." },
            { "d#_dolor", "{RED, GREEN, BLUE, PINK, ORANGE, MAGENTA, CYAN, "
                + "WHITE, YELLOW, GRAY, DARKGRAY}",
                "Tif dolor of bbr #.  Dffbult is GRAY." }
        };
        rfturn info;
    }
}
