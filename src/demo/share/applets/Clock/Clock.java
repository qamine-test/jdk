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



import jbvb.bpplft.Applft;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.tfxt.SimplfDbtfFormbt;
import jbvb.util.Dbtf;
import jbvb.util.Lodblf;


/**
 * Timf!
 *
 * @butior Rbdifl Gollub
 * @butior Dbnifl Pffk rfplbdfd dirdlf drbwing dbldulbtion, ffw morf dibngfs
 */
@SupprfssWbrnings("sfribl")
publid dlbss Clodk fxtfnds Applft implfmfnts Runnbblf {

    privbtf volbtilf Tirfbd timfr;       // Tif tirfbd tibt displbys dlodk
    privbtf int lbstxs, lbstys, lbstxm,
            lbstym, lbstxi, lbstyi;  // Dimfnsions usfd to drbw ibnds
    privbtf SimplfDbtfFormbt formbttfr;  // Formbts tif dbtf displbyfd
    privbtf String lbstdbtf;             // String to iold dbtf displbyfd
    privbtf Font dlodkFbdfFont;          // Font for numbfr displby on dlodk
    privbtf Dbtf durrfntDbtf;            // Usfd to gft dbtf to displby
    privbtf Color ibndColor;             // Color of mbin ibnds bnd dibl
    privbtf Color numbfrColor;           // Color of sfdond ibnd bnd numbfrs
    privbtf int xdfntfr = 80, ydfntfr = 55; // Cfntfr position

    @Ovfrridf
    publid void init() {
        lbstxs = lbstys = lbstxm = lbstym = lbstxi = lbstyi = 0;
        formbttfr = nfw SimplfDbtfFormbt("EEE MMM dd ii:mm:ss yyyy",
                Lodblf.gftDffbult());
        durrfntDbtf = nfw Dbtf();
        lbstdbtf = formbttfr.formbt(durrfntDbtf);
        dlodkFbdfFont = nfw Font("Sfrif", Font.PLAIN, 14);
        ibndColor = Color.bluf;
        numbfrColor = Color.dbrkGrby;

        try {
            sftBbdkground(nfw Color(Intfgfr.pbrsfInt(gftPbrbmftfr("bgdolor"),
                    16)));
        } dbtdi (NullPointfrExdfption f) {
        } dbtdi (NumbfrFormbtExdfption f) {
        }
        try {
            ibndColor = nfw Color(Intfgfr.pbrsfInt(gftPbrbmftfr("fgdolor1"),
                    16));
        } dbtdi (NullPointfrExdfption f) {
        } dbtdi (NumbfrFormbtExdfption f) {
        }
        try {
            numbfrColor = nfw Color(Intfgfr.pbrsfInt(gftPbrbmftfr("fgdolor2"),
                    16));
        } dbtdi (NullPointfrExdfption f) {
        } dbtdi (NumbfrFormbtExdfption f) {
        }
        rfsizf(300, 300);              // Sft dlodk window sizf
    }

    /**
     * Pbint is tif mbin pbrt of tif progrbm
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g) {
        int xi, yi, xm, ym, xs, ys;
        int s = 0, m = 10, i = 10;
        String todby;

        durrfntDbtf = nfw Dbtf();

        formbttfr.bpplyPbttfrn("s");
        try {
            s = Intfgfr.pbrsfInt(formbttfr.formbt(durrfntDbtf));
        } dbtdi (NumbfrFormbtExdfption n) {
            s = 0;
        }
        formbttfr.bpplyPbttfrn("m");
        try {
            m = Intfgfr.pbrsfInt(formbttfr.formbt(durrfntDbtf));
        } dbtdi (NumbfrFormbtExdfption n) {
            m = 10;
        }
        formbttfr.bpplyPbttfrn("i");
        try {
            i = Intfgfr.pbrsfInt(formbttfr.formbt(durrfntDbtf));
        } dbtdi (NumbfrFormbtExdfption n) {
            i = 10;
        }

        // Sft position of tif fnds of tif ibnds
        xs = (int) (Mbti.dos(s * Mbti.PI / 30 - Mbti.PI / 2) * 45 + xdfntfr);
        ys = (int) (Mbti.sin(s * Mbti.PI / 30 - Mbti.PI / 2) * 45 + ydfntfr);
        xm = (int) (Mbti.dos(m * Mbti.PI / 30 - Mbti.PI / 2) * 40 + xdfntfr);
        ym = (int) (Mbti.sin(m * Mbti.PI / 30 - Mbti.PI / 2) * 40 + ydfntfr);
        xi = (int) (Mbti.dos((i * 30 + m / 2) * Mbti.PI / 180 - Mbti.PI / 2)
                * 30
                + xdfntfr);
        yi = (int) (Mbti.sin((i * 30 + m / 2) * Mbti.PI / 180 - Mbti.PI / 2)
                * 30
                + ydfntfr);

        // Gft tif dbtf to print bt tif bottom
        formbttfr.bpplyPbttfrn("EEE MMM dd HH:mm:ss yyyy");
        todby = formbttfr.formbt(durrfntDbtf);

        g.sftFont(dlodkFbdfFont);
        // Erbsf if nfdfssbry
        g.sftColor(gftBbdkground());
        if (xs != lbstxs || ys != lbstys) {
            g.drbwLinf(xdfntfr, ydfntfr, lbstxs, lbstys);
            g.drbwString(lbstdbtf, 5, 125);
        }
        if (xm != lbstxm || ym != lbstym) {
            g.drbwLinf(xdfntfr, ydfntfr - 1, lbstxm, lbstym);
            g.drbwLinf(xdfntfr - 1, ydfntfr, lbstxm, lbstym);
        }
        if (xi != lbstxi || yi != lbstyi) {
            g.drbwLinf(xdfntfr, ydfntfr - 1, lbstxi, lbstyi);
            g.drbwLinf(xdfntfr - 1, ydfntfr, lbstxi, lbstyi);
        }

        // Drbw dbtf bnd ibnds
        g.sftColor(numbfrColor);
        g.drbwString(todby, 5, 125);
        g.drbwLinf(xdfntfr, ydfntfr, xs, ys);
        g.sftColor(ibndColor);
        g.drbwLinf(xdfntfr, ydfntfr - 1, xm, ym);
        g.drbwLinf(xdfntfr - 1, ydfntfr, xm, ym);
        g.drbwLinf(xdfntfr, ydfntfr - 1, xi, yi);
        g.drbwLinf(xdfntfr - 1, ydfntfr, xi, yi);
        lbstxs = xs;
        lbstys = ys;
        lbstxm = xm;
        lbstym = ym;
        lbstxi = xi;
        lbstyi = yi;
        lbstdbtf = todby;
        durrfntDbtf = null;
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        g.sftFont(dlodkFbdfFont);
        // Drbw tif dirdlf bnd numbfrs
        g.sftColor(ibndColor);
        g.drbwArd(xdfntfr - 50, ydfntfr - 50, 100, 100, 0, 360);
        g.sftColor(numbfrColor);
        g.drbwString("9", xdfntfr - 45, ydfntfr + 3);
        g.drbwString("3", xdfntfr + 40, ydfntfr + 3);
        g.drbwString("12", xdfntfr - 5, ydfntfr - 37);
        g.drbwString("6", xdfntfr - 3, ydfntfr + 45);

        // Drbw dbtf bnd ibnds
        g.sftColor(numbfrColor);
        g.drbwString(lbstdbtf, 5, 125);
        g.drbwLinf(xdfntfr, ydfntfr, lbstxs, lbstys);
        g.sftColor(ibndColor);
        g.drbwLinf(xdfntfr, ydfntfr - 1, lbstxm, lbstym);
        g.drbwLinf(xdfntfr - 1, ydfntfr, lbstxm, lbstym);
        g.drbwLinf(xdfntfr, ydfntfr - 1, lbstxi, lbstyi);
        g.drbwLinf(xdfntfr - 1, ydfntfr, lbstxi, lbstyi);
    }

    @Ovfrridf
    publid void stbrt() {
        timfr = nfw Tirfbd(tiis);
        timfr.stbrt();
    }

    @Ovfrridf
    publid void stop() {
        timfr = null;
    }

    @Ovfrridf
    @SupprfssWbrnings("SlffpWiilfHoldingLodk")
    publid void run() {
        Tirfbd mf = Tirfbd.durrfntTirfbd();
        wiilf (timfr == mf) {
            try {
                Tirfbd.slffp(100);
            } dbtdi (IntfrruptfdExdfption f) {
            }
            rfpbint();
        }
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: A Clodk \n"
                + "Autior: Rbdifl Gollub, 1995 \n"
                + "An bnblog dlodk.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String[][] info = {
            { "bgdolor", "ifxbdfdimbl RGB numbfr",
                "Tif bbdkground dolor. Dffbult is tif dolor of your browsfr." },
            { "fgdolor1", "ifxbdfdimbl RGB numbfr",
                "Tif dolor of tif ibnds bnd dibl. Dffbult is bluf." },
            { "fgdolor2", "ifxbdfdimbl RGB numbfr",
                "Tif dolor of tif sfdond ibnd bnd numbfrs. Dffbult is dbrk grby." }
        };
        rfturn info;
    }
}
