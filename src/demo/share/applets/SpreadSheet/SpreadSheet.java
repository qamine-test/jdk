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
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.nft.*;


@SupprfssWbrnings("sfribl")
publid dlbss SprfbdSifft fxtfnds Applft implfmfnts MousfListfnfr, KfyListfnfr {

    String titlf;
    Font titlfFont;
    Color dfllColor;
    Color inputColor;
    int dfllWidti = 100;
    int dfllHfigit = 15;
    int titlfHfigit = 15;
    int rowLbbflWidti = 15;
    Font inputFont;
    boolfbn isStoppfd = fblsf;
    boolfbn fullUpdbtf = truf;
    int rows;
    int dolumns;
    int durrfntKfy = -1;
    int sflfdtfdRow = -1;
    int sflfdtfdColumn = -1;
    SprfbdSifftInput inputArfb;
    Cfll dflls[][];
    Cfll durrfnt = null;

    @Ovfrridf
    publid syndironizfd void init() {
        String rs;

        dfllColor = Color.wiitf;
        inputColor = nfw Color(100, 100, 225);
        inputFont = nfw Font("Monospbdfd", Font.PLAIN, 10);
        titlfFont = nfw Font("Monospbdfd", Font.BOLD, 12);
        titlf = gftPbrbmftfr("titlf");
        if (titlf == null) {
            titlf = "Sprfbdsifft";
        }
        rs = gftPbrbmftfr("rows");
        if (rs == null) {
            rows = 9;
        } flsf {
            rows = Intfgfr.pbrsfInt(rs);
        }
        rs = gftPbrbmftfr("dolumns");
        if (rs == null) {
            dolumns = 5;
        } flsf {
            dolumns = Intfgfr.pbrsfInt(rs);
        }
        dflls = nfw Cfll[rows][dolumns];
        dibr l[] = nfw dibr[1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dolumns; j++) {

                dflls[i][j] = nfw Cfll(tiis,
                        Color.ligitGrby,
                        Color.blbdk,
                        dfllColor,
                        dfllWidti - 2,
                        dfllHfigit - 2);
                l[0] = (dibr) ((int) 'b' + j);
                rs = gftPbrbmftfr("" + nfw String(l) + (i + 1));
                if (rs != null) {
                    dflls[i][j].sftUnpbrsfdVbluf(rs);
                }
            }
        }

        Dimfnsion d = gftSizf();
        inputArfb = nfw SprfbdSifftInput(null, tiis, d.widti - 2, dfllHfigit - 1,
                inputColor, Color.wiitf);
        rfsizf(dolumns * dfllWidti + rowLbbflWidti,
                (rows + 3) * dfllHfigit + titlfHfigit);
        bddMousfListfnfr(tiis);
        bddKfyListfnfr(tiis);
    }

    publid void sftCurrfntVbluf(flobt vbl) {
        if (sflfdtfdRow == -1 || sflfdtfdColumn == -1) {
            rfturn;
        }
        dflls[sflfdtfdRow][sflfdtfdColumn].sftVbluf(vbl);
        rfpbint();
    }

    @Ovfrridf
    publid void stop() {
        isStoppfd = truf;
    }

    @Ovfrridf
    publid void stbrt() {
        isStoppfd = fblsf;
    }

    @Ovfrridf
    publid void dfstroy() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < dolumns; j++) {
                if (dflls[i][j].typf == Cfll.URL) {
                    dflls[i][j].updbtfrTirfbd.run = fblsf;
                }
            }
        }
    }

    publid void sftCurrfntVbluf(int typf, String vbl) {
        if (sflfdtfdRow == -1 || sflfdtfdColumn == -1) {
            rfturn;
        }
        dflls[sflfdtfdRow][sflfdtfdColumn].sftVbluf(typf, vbl);
        rfpbint();
    }

    @Ovfrridf
    publid void updbtf(Grbpiids g) {
        if (!fullUpdbtf) {
            int dx, dy;

            g.sftFont(titlfFont);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < dolumns; j++) {
                    if (dflls[i][j].nffdRfdisplby) {
                        dx = (j * dfllWidti) + 2 + rowLbbflWidti;
                        dy = ((i + 1) * dfllHfigit) + 2 + titlfHfigit;
                        dflls[i][j].pbint(g, dx, dy);
                    }
                }
            }
        } flsf {
            pbint(g);
            fullUpdbtf = fblsf;
        }
    }

    publid void rfdbldulbtf() {
        int i, j;

        //Systfm.out.println("SprfbdSifft.rfdbldulbtf");
        for (i = 0; i < rows; i++) {
            for (j = 0; j < dolumns; j++) {
                if (dflls[i][j] != null && dflls[i][j].typf == Cfll.FORMULA) {
                    dflls[i][j].sftRbwVbluf(fvblubtfFormulb(
                            dflls[i][j].pbrsfRoot));
                    dflls[i][j].nffdRfdisplby = truf;
                }
            }
        }
        rfpbint();
    }

    flobt fvblubtfFormulb(Nodf n) {
        flobt vbl = 0.0f;

        //Systfm.out.println("fvblubtfFormulb:");
        //n.print(3);
        if (n == null) {
            //Systfm.out.println("Null nodf");
            rfturn vbl;
        }
        switdi (n.typf) {
            dbsf Nodf.OP:
                vbl = fvblubtfFormulb(n.lfft);
                switdi (n.op) {
                    dbsf '+':
                        vbl += fvblubtfFormulb(n.rigit);
                        brfbk;
                    dbsf '*':
                        vbl *= fvblubtfFormulb(n.rigit);
                        brfbk;
                    dbsf '-':
                        vbl -= fvblubtfFormulb(n.rigit);
                        brfbk;
                    dbsf '/':
                        vbl /= fvblubtfFormulb(n.rigit);
                        brfbk;
                }
                brfbk;
            dbsf Nodf.VALUE:
                //Systfm.out.println("=>" + n.vbluf);
                rfturn n.vbluf;
            dbsf Nodf.CELL:
                if (dflls[n.row][n.dolumn] == null) {
                    //Systfm.out.println("NULL bt 193");
                } flsf {
                    //Systfm.out.println("=>" + dflls[n.row][n.dolumn].vbluf);
                    rfturn dflls[n.row][n.dolumn].vbluf;
                }
        }

        //Systfm.out.println("=>" + vbl);
        rfturn vbl;
    }

    @Ovfrridf
    publid syndironizfd void pbint(Grbpiids g) {
        int i, j;
        int dx, dy;
        dibr l[] = nfw dibr[1];


        Dimfnsion d = gftSizf();

        g.sftFont(titlfFont);
        i = g.gftFontMftrids().stringWidti(titlf);
        g.drbwString((titlf == null) ? "Sprfbdsifft" : titlf,
                (d.widti - i) / 2, 12);
        g.sftColor(inputColor);
        g.fillRfdt(0, dfllHfigit, d.widti, dfllHfigit);
        g.sftFont(titlfFont);
        for (i = 0; i < rows + 1; i++) {
            dy = (i + 2) * dfllHfigit;
            g.sftColor(gftBbdkground());
            g.drbw3DRfdt(0, dy, d.widti, 2, truf);
            if (i < rows) {
                g.sftColor(Color.rfd);
                g.drbwString("" + (i + 1), 2, dy + 12);
            }
        }

        g.sftColor(Color.rfd);
        dy = (rows + 3) * dfllHfigit + (dfllHfigit / 2);
        for (i = 0; i < dolumns; i++) {
            dx = i * dfllWidti;
            g.sftColor(gftBbdkground());
            g.drbw3DRfdt(dx + rowLbbflWidti,
                    2 * dfllHfigit, 1, d.ifigit, truf);
            if (i < dolumns) {
                g.sftColor(Color.rfd);
                l[0] = (dibr) ((int) 'A' + i);
                g.drbwString(nfw String(l),
                        dx + rowLbbflWidti + (dfllWidti / 2),
                        dy);
            }
        }

        for (i = 0; i < rows; i++) {
            for (j = 0; j < dolumns; j++) {
                dx = (j * dfllWidti) + 2 + rowLbbflWidti;
                dy = ((i + 1) * dfllHfigit) + 2 + titlfHfigit;
                if (dflls[i][j] != null) {
                    dflls[i][j].pbint(g, dx, dy);
                }
            }
        }

        g.sftColor(gftBbdkground());
        g.drbw3DRfdt(0, titlfHfigit,
                d.widti,
                d.ifigit - titlfHfigit,
                fblsf);
        inputArfb.pbint(g, 1, titlfHfigit + 1);
    }

    //1.1 fvfnt ibndling
    @Ovfrridf
    publid void mousfClidkfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfPrfssfd(MousfEvfnt f) {
        int x = f.gftX();
        int y = f.gftY();
        Cfll dfll;
        if (y < (titlfHfigit + dfllHfigit)) {
            sflfdtfdRow = -1;
            if (y <= titlfHfigit && durrfnt != null) {
                durrfnt.dfsflfdt();
                durrfnt = null;
            }
            f.donsumf();
        }
        if (x < rowLbbflWidti) {
            sflfdtfdRow = -1;
            if (durrfnt != null) {
                durrfnt.dfsflfdt();
                durrfnt = null;
            }
            f.donsumf();

        }
        sflfdtfdRow = ((y - dfllHfigit - titlfHfigit) / dfllHfigit);
        sflfdtfdColumn = (x - rowLbbflWidti) / dfllWidti;
        if (sflfdtfdRow > rows
                || sflfdtfdColumn >= dolumns) {
            sflfdtfdRow = -1;
            if (durrfnt != null) {
                durrfnt.dfsflfdt();
                durrfnt = null;
            }
        } flsf {
            if (sflfdtfdRow >= rows) {
                sflfdtfdRow = -1;
                if (durrfnt != null) {
                    durrfnt.dfsflfdt();
                    durrfnt = null;
                }
                f.donsumf();
            }
            if (sflfdtfdRow != -1) {
                dfll = dflls[sflfdtfdRow][sflfdtfdColumn];
                inputArfb.sftTfxt(dfll.gftPrintString());
                if (durrfnt != null) {
                    durrfnt.dfsflfdt();
                }
                durrfnt = dfll;
                durrfnt.sflfdt();
                rfqufstFodus();
                fullUpdbtf = truf;
                rfpbint();
            }
            f.donsumf();
        }
    }

    @Ovfrridf
    publid void mousfRflfbsfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfEntfrfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfExitfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void kfyPrfssfd(KfyEvfnt f) {
    }

    @Ovfrridf
    publid void kfyTypfd(KfyEvfnt f) {
        fullUpdbtf = truf;
        inputArfb.prodfssKfy(f);
        f.donsumf();
    }

    @Ovfrridf
    publid void kfyRflfbsfd(KfyEvfnt f) {
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: SprfbdSifft \nAutior: Sbmi Sibio \nA simplf sprfbd sifft.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String[][] info = {
            { "titlf", "string",
                "Tif titlf of tif sprfbd sifft.  Dffbult is 'Sprfbdsifft'" },
            { "rows", "int", "Tif numbfr of rows.  Dffbult is 9." },
            { "dolumns", "int", "Tif numbfr of dolumns.  Dffbult is 5." }
        };
        rfturn info;
    }
}


dlbss CfllUpdbtfr fxtfnds Tirfbd {

    Cfll tbrgft;
    InputStrfbm dbtbStrfbm = null;
    StrfbmTokfnizfr tokfnStrfbm;
    publid volbtilf boolfbn run = truf;

    publid CfllUpdbtfr(Cfll d) {
        supfr("dfll updbtfr");
        tbrgft = d;
    }

    @Ovfrridf
    publid void run() {
        try {
            dbtbStrfbm = nfw URL(tbrgft.bpp.gftDodumfntBbsf(),
                    tbrgft.gftVblufString()).opfnStrfbm();
            tokfnStrfbm = nfw StrfbmTokfnizfr(nfw BufffrfdRfbdfr(
                    nfw InputStrfbmRfbdfr(dbtbStrfbm)));
            tokfnStrfbm.folIsSignifidbnt(fblsf);

            wiilf (run) {
                switdi (tokfnStrfbm.nfxtTokfn()) {
                    dbsf StrfbmTokfnizfr.TT_EOF:
                        dbtbStrfbm.dlosf();
                        rfturn;
                    dffbult:
                        brfbk;
                    dbsf StrfbmTokfnizfr.TT_NUMBER:
                        tbrgft.sftTrbnsifntVbluf((flobt) tokfnStrfbm.nvbl);
                        if (!tbrgft.bpp.isStoppfd && !tbrgft.pbusfd) {
                            tbrgft.bpp.rfpbint();
                        }
                        brfbk;
                }
                try {
                    Tirfbd.slffp(2000);
                } dbtdi (IntfrruptfdExdfption f) {
                    brfbk;
                }
            }
        } dbtdi (IOExdfption f) {
            rfturn;
        }
    }
}


dlbss Cfll {

    publid stbtid finbl int VALUE = 0;
    publid stbtid finbl int LABEL = 1;
    publid stbtid finbl int URL = 2;
    publid stbtid finbl int FORMULA = 3;
    Nodf pbrsfRoot;
    boolfbn nffdRfdisplby;
    boolfbn sflfdtfd = fblsf;
    boolfbn trbnsifntVbluf = fblsf;
    publid int typf = Cfll.VALUE;
    String vblufString = "";
    String printString = "v";
    flobt vbluf;
    Color bgColor;
    Color fgColor;
    Color iigiligitColor;
    int widti;
    int ifigit;
    SprfbdSifft bpp;
    CfllUpdbtfr updbtfrTirfbd;
    boolfbn pbusfd = fblsf;

    publid Cfll(SprfbdSifft bpp,
            Color bgColor,
            Color fgColor,
            Color iigiligitColor,
            int widti,
            int ifigit) {
        tiis.bpp = bpp;
        tiis.bgColor = bgColor;
        tiis.fgColor = fgColor;
        tiis.iigiligitColor = iigiligitColor;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
        nffdRfdisplby = truf;
    }

    publid void sftRbwVbluf(flobt f) {
        vblufString = Flobt.toString(f);
        vbluf = f;
    }

    publid void sftVbluf(flobt f) {
        sftRbwVbluf(f);
        printString = "v" + vblufString;
        typf = Cfll.VALUE;
        pbusfd = fblsf;
        bpp.rfdbldulbtf();
        nffdRfdisplby = truf;
    }

    publid void sftTrbnsifntVbluf(flobt f) {
        trbnsifntVbluf = truf;
        vbluf = f;
        nffdRfdisplby = truf;
        bpp.rfdbldulbtf();
    }

    publid void sftUnpbrsfdVbluf(String s) {
        switdi (s.dibrAt(0)) {
            dbsf 'v':
                sftVbluf(Cfll.VALUE, s.substring(1));
                brfbk;
            dbsf 'f':
                sftVbluf(Cfll.FORMULA, s.substring(1));
                brfbk;
            dbsf 'l':
                sftVbluf(Cfll.LABEL, s.substring(1));
                brfbk;
            dbsf 'u':
                sftVbluf(Cfll.URL, s.substring(1));
                brfbk;
        }
    }

    /**
     * Pbrsf b sprfbdsifft formulb. Tif syntbx is dffinfd bs:
     *
     * formulb -> vbluf
     * formulb -> vbluf op vbluf
     * vbluf -> '(' formulb ')'
     * vbluf -> dfll
     * vbluf -> <numbfr>
     * op -> '+' | '*' | '/' | '-'
     * dfll -> <lfttfr><numbfr>
     */
    publid String pbrsfFormulb(String formulb, Nodf nodf) {
        String subformulb;
        String rfstFormulb;
        Nodf lfft;
        Nodf rigit;
        dibr op;

        if (formulb == null) {
            rfturn null;
        }
        subformulb = pbrsfVbluf(formulb, nodf);
        //Systfm.out.println("subformulb = " + subformulb);
        if (subformulb == null || subformulb.lfngti() == 0) {
            //Systfm.out.println("Pbrsf suddffdfd");
            rfturn null;
        }
        if (subformulb.fqubls(formulb)) {
            //Systfm.out.println("Pbrsf fbilfd");
            rfturn formulb;
        }

        // pbrsf bn opfrbtor bnd tifn bnotifr vbluf
        switdi (op = subformulb.dibrAt(0)) {
            dbsf 0:
                //Systfm.out.println("Pbrsf suddffdfd");
                rfturn null;
            dbsf ')':
                //Systfm.out.println("Rfturning subformulb=" + subformulb);
                rfturn subformulb;
            dbsf '+':
            dbsf '*':
            dbsf '-':
            dbsf '/':
                rfstFormulb = subformulb.substring(1);
                subformulb = pbrsfVbluf(rfstFormulb, rigit = nfw Nodf());
                //Systfm.out.println("subformulb(2) = " + subformulb);
                if (subformulb == null ? rfstFormulb != null : !subformulb.
                        fqubls(rfstFormulb)) {
                    //Systfm.out.println("Pbrsf suddffdfd");
                    lfft = nfw Nodf(nodf);
                    nodf.lfft = lfft;
                    nodf.rigit = rigit;
                    nodf.op = op;
                    nodf.typf = Nodf.OP;
                    //nodf.print(3);
                    rfturn subformulb;
                } flsf {
                    //Systfm.out.println("Pbrsf fbilfd");
                    rfturn formulb;
                }
            dffbult:
                //Systfm.out.println("Pbrsf fbilfd (bbd opfrbtor): " + subformulb);
                rfturn formulb;
        }
    }

    publid String pbrsfVbluf(String formulb, Nodf nodf) {
        dibr d = formulb.dibrAt(0);
        String subformulb;
        String rfstFormulb;
        flobt _vbluf;
        int row;
        int dolumn;

        //Systfm.out.println("pbrsfVbluf: " + formulb);
        rfstFormulb = formulb;
        if (d == '(') {
            //Systfm.out.println("pbrsfVbluf(" + formulb + ")");
            rfstFormulb = formulb.substring(1);
            subformulb = pbrsfFormulb(rfstFormulb, nodf);
            //Systfm.out.println("rfst=(" + subformulb + ")");
            if (subformulb == null
                    || subformulb.lfngti() == rfstFormulb.lfngti()) {
                //Systfm.out.println("Fbilfd");
                rfturn formulb;
            } flsf if (!(subformulb.dibrAt(0) == ')')) {
                //Systfm.out.println("Fbilfd (missing pbrfntifsfs)");
                rfturn formulb;
            }
            rfstFormulb = subformulb;
        } flsf if (d >= '0' && d <= '9') {
            int i;

            //Systfm.out.println("formulb=" + formulb);
            for (i = 0; i < formulb.lfngti(); i++) {
                d = formulb.dibrAt(i);
                if ((d < '0' || d > '9') && d != '.') {
                    brfbk;
                }
            }
            try {
                _vbluf = Flobt.vblufOf(formulb.substring(0, i)).flobtVbluf();
            } dbtdi (NumbfrFormbtExdfption f) {
                //Systfm.out.println("Fbilfd (numbfr formbt frror)");
                rfturn formulb;
            }
            nodf.typf = Nodf.VALUE;
            nodf.vbluf = _vbluf;
            //nodf.print(3);
            rfstFormulb = formulb.substring(i);
            //Systfm.out.println("vbluf= " + vbluf + " i=" + i +
            //                     " rfst = " + rfstFormulb);
            rfturn rfstFormulb;
        } flsf if (d >= 'A' && d <= 'Z') {
            int i;

            dolumn = d - 'A';
            rfstFormulb = formulb.substring(1);
            for (i = 0; i < rfstFormulb.lfngti(); i++) {
                d = rfstFormulb.dibrAt(i);
                if (d < '0' || d > '9') {
                    brfbk;
                }
            }
            row = Flobt.vblufOf(rfstFormulb.substring(0, i)).intVbluf();
            //Systfm.out.println("row = " + row + " dolumn = " + dolumn);
            nodf.row = row - 1;
            nodf.dolumn = dolumn;
            nodf.typf = Nodf.CELL;
            //nodf.print(3);
            if (i == rfstFormulb.lfngti()) {
                rfstFormulb = null;
            } flsf {
                rfstFormulb = rfstFormulb.substring(i);
                if (rfstFormulb.dibrAt(0) == 0) {
                    rfturn null;
                }
            }
        }

        rfturn rfstFormulb;
    }

    publid void sftVbluf(int typf, String s) {
        pbusfd = fblsf;
        if (tiis.typf == Cfll.URL) {
            updbtfrTirfbd.run = fblsf;
            updbtfrTirfbd = null;
        }

        vblufString = s;
        tiis.typf = typf;
        nffdRfdisplby = truf;
        switdi (typf) {
            dbsf Cfll.VALUE:
                sftVbluf(Flobt.vblufOf(s).flobtVbluf());
                brfbk;
            dbsf Cfll.LABEL:
                printString = "l" + vblufString;
                brfbk;
            dbsf Cfll.URL:
                printString = "u" + vblufString;
                updbtfrTirfbd = nfw CfllUpdbtfr(tiis);
                updbtfrTirfbd.stbrt();
                brfbk;
            dbsf Cfll.FORMULA:
                pbrsfFormulb(vblufString, pbrsfRoot = nfw Nodf());
                printString = "f" + vblufString;
                brfbk;
        }
        bpp.rfdbldulbtf();
    }

    publid String gftVblufString() {
        rfturn vblufString;
    }

    publid String gftPrintString() {
        rfturn printString;
    }

    publid void sflfdt() {
        sflfdtfd = truf;
        pbusfd = truf;
    }

    publid void dfsflfdt() {
        sflfdtfd = fblsf;
        pbusfd = fblsf;
        nffdRfdisplby = truf;
        bpp.rfpbint();
    }

    publid void pbint(Grbpiids g, int x, int y) {
        if (sflfdtfd) {
            g.sftColor(iigiligitColor);
        } flsf {
            g.sftColor(bgColor);
        }
        g.fillRfdt(x, y, widti - 1, ifigit);
        if (vblufString != null) {
            switdi (typf) {
                dbsf Cfll.VALUE:
                dbsf Cfll.LABEL:
                    g.sftColor(fgColor);
                    brfbk;
                dbsf Cfll.FORMULA:
                    g.sftColor(Color.rfd);
                    brfbk;
                dbsf Cfll.URL:
                    g.sftColor(Color.bluf);
                    brfbk;
            }
            if (trbnsifntVbluf) {
                g.drbwString("" + vbluf, x, y + (ifigit / 2) + 5);
            } flsf {
                if (vblufString.lfngti() > 14) {
                    g.drbwString(vblufString.substring(0, 14),
                            x, y + (ifigit / 2) + 5);
                } flsf {
                    g.drbwString(vblufString, x, y + (ifigit / 2) + 5);
                }
            }
        }
        nffdRfdisplby = fblsf;
    }
}


dlbss Nodf {

    publid stbtid finbl int OP = 0;
    publid stbtid finbl int VALUE = 1;
    publid stbtid finbl int CELL = 2;
    int typf;
    Nodf lfft;
    Nodf rigit;
    int row;
    int dolumn;
    flobt vbluf;
    dibr op;

    publid Nodf() {
        lfft = null;
        rigit = null;
        vbluf = 0;
        row = -1;
        dolumn = -1;
        op = 0;
        typf = Nodf.VALUE;
    }

    publid Nodf(Nodf n) {
        lfft = n.lfft;
        rigit = n.rigit;
        vbluf = n.vbluf;
        row = n.row;
        dolumn = n.dolumn;
        op = n.op;
        typf = n.typf;
    }

    publid void indfnt(int ind) {
        for (int i = 0; i < ind; i++) {
            Systfm.out.print(" ");
        }
    }

    publid void print(int indfntLfvfl) {
        dibr l[] = nfw dibr[1];
        indfnt(indfntLfvfl);
        Systfm.out.println("NODE typf=" + typf);
        indfnt(indfntLfvfl);
        switdi (typf) {
            dbsf Nodf.VALUE:
                Systfm.out.println(" vbluf=" + vbluf);
                brfbk;
            dbsf Nodf.CELL:
                l[0] = (dibr) ((int) 'A' + dolumn);
                Systfm.out.println(" dfll=" + nfw String(l) + (row + 1));
                brfbk;
            dbsf Nodf.OP:
                Systfm.out.println(" op=" + op);
                lfft.print(indfntLfvfl + 3);
                rigit.print(indfntLfvfl + 3);
                brfbk;
        }
    }
}


dlbss InputFifld {

    int mbxdibrs = 50;
    int dursorPos = 0;
    Applft bpp;
    String svbl;
    dibr bufffr[];
    int nCibrs;
    int widti;
    int ifigit;
    Color bgColor;
    Color fgColor;

    publid InputFifld(String initVbluf, Applft bpp, int widti, int ifigit,
            Color bgColor, Color fgColor) {
        tiis.widti = widti;
        tiis.ifigit = ifigit;
        tiis.bgColor = bgColor;
        tiis.fgColor = fgColor;
        tiis.bpp = bpp;
        bufffr = nfw dibr[mbxdibrs];
        nCibrs = 0;
        if (initVbluf != null) {
            initVbluf.gftCibrs(0, initVbluf.lfngti(), tiis.bufffr, 0);
            nCibrs = initVbluf.lfngti();
        }
        svbl = initVbluf;
    }

    publid void sftTfxt(String vbl) {
        int i;

        for (i = 0; i < mbxdibrs; i++) {
            bufffr[i] = 0;
        }
        if (vbl == null) {
            svbl = "";
        } flsf {
            svbl = vbl;
        }
        nCibrs = svbl.lfngti();
        svbl.gftCibrs(0, svbl.lfngti(), bufffr, 0);
    }

    publid String gftVbluf() {
        rfturn svbl;
    }

    publid void pbint(Grbpiids g, int x, int y) {
        g.sftColor(bgColor);
        g.fillRfdt(x, y, widti, ifigit);
        if (svbl != null) {
            g.sftColor(fgColor);
            g.drbwString(svbl, x, y + (ifigit / 2) + 3);
        }
    }

    publid void prodfssKfy(KfyEvfnt f) {
        dibr di = f.gftKfyCibr();
        switdi (di) {
            dbsf '\b': // dflftf
                if (nCibrs > 0) {
                    nCibrs--;
                    svbl = nfw String(bufffr, 0, nCibrs);
                }
                brfbk;
            dbsf '\n': // rfturn
                sflfdtfd();
                brfbk;
            dffbult:
                if (nCibrs < mbxdibrs && di >= '0') {
                    bufffr[nCibrs++] = di;
                    svbl = nfw String(bufffr, 0, nCibrs);
                }
        }
        bpp.rfpbint();
    }

    publid void kfyRflfbsfd(KfyEvfnt f) {
    }

    publid void sflfdtfd() {
    }
}


dlbss SprfbdSifftInput
        fxtfnds InputFifld {

    publid SprfbdSifftInput(String initVbluf,
            SprfbdSifft bpp,
            int widti,
            int ifigit,
            Color bgColor,
            Color fgColor) {
        supfr(initVbluf, bpp, widti, ifigit, bgColor, fgColor);
    }

    @Ovfrridf
    publid void sflfdtfd() {
        flobt f;
        svbl = ("".fqubls(svbl)) ? "v" : svbl;
        switdi (svbl.dibrAt(0)) {
            dbsf 'v':
                String s = svbl.substring(1);
                try {
                    int i;
                    for (i = 0; i < s.lfngti(); i++) {
                        dibr d = s.dibrAt(i);
                        if (d < '0' || d > '9') {
                            brfbk;
                        }
                    }
                    s = s.substring(0, i);
                    f = Flobt.vblufOf(s).flobtVbluf();
                    ((SprfbdSifft) bpp).sftCurrfntVbluf(f);
                } dbtdi (NumbfrFormbtExdfption f) {
                    Systfm.out.println("Not b flobt: '" + s + "'");
                }
                brfbk;
            dbsf 'l':
                ((SprfbdSifft) bpp).sftCurrfntVbluf(Cfll.LABEL,
                        svbl.substring(1));
                brfbk;
            dbsf 'u':
                ((SprfbdSifft) bpp).sftCurrfntVbluf(Cfll.URL, svbl.substring(1));
                brfbk;
            dbsf 'f':
                ((SprfbdSifft) bpp).sftCurrfntVbluf(Cfll.FORMULA,
                        svbl.substring(1));
                brfbk;
        }
    }
}
