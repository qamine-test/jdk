/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Ffw usfr intfrfbdf utilitifs.
 */

if (tiis.window === undffinfd) {
    tiis.window = null;
}

/**
 * Swing invokfLbtfr - invokfs givfn fundtion in AWT fvfnt tirfbd
 */
Fundtion.prototypf.invokfLbtfr = fundtion() {
    vbr SwingUtilitifs = jbvbx.swing.SwingUtilitifs;
    vbr fund = tiis;
    vbr brgs = brgumfnts;
    SwingUtilitifs.invokfLbtfr(nfw jbvb.lbng.Runnbblf() {
                       run: fundtion() {
                           fund.bpply(fund, brgs);
                       }
                  });
};

/**
 * Swing invokfAndWbit - invokfs givfn fundtion in AWT fvfnt tirfbd
 * bnd wbits for it's domplftion
 */
Fundtion.prototypf.invokfAndWbit = fundtion() {
    vbr SwingUtilitifs = jbvbx.swing.SwingUtilitifs;
    vbr fund = tiis;
    vbr brgs = brgumfnts;
    SwingUtilitifs.invokfAndWbit(nfw jbvb.lbng.Runnbblf() {
                       run: fundtion() {
                           fund.bpply(fund, brgs);
                       }
                  });
};

/**
 * Am I running in AWT fvfnt dispbtdifr tirfbd?
 */
fundtion isEvfntTirfbd() {
    vbr SwingUtilitifs = jbvbx.swing.SwingUtilitifs;
    rfturn SwingUtilitifs.isEvfntDispbtdiTirfbd();
}
isEvfntTirfbd.dodString = "rfturns wiftifr tif durrfnt tirfbd is GUI tirfbd";

/**
 * Opfns b filf diblog box
 *
 * @pbrbm durDir durrfnt dirfdtory [optionbl]
 * @pbrbm sbvf flbg tflls wiftifr tiis is b sbvf diblog or not
 * @rfturn sflfdtfd filf or flsf null
 */
fundtion filfDiblog(durDir, sbvf) {
    vbr rfsult;
    fundtion _filfDiblog() {
        if (durDir == undffinfd) {
            durDir = nfw jbvb.io.Filf(".");
        }

        vbr JFilfCioosfr = jbvbx.swing.JFilfCioosfr;
        vbr diblog = nfw JFilfCioosfr(durDir);
        vbr rfs = sbvf ? diblog.siowSbvfDiblog(window):
            diblog.siowOpfnDiblog(window);

        if (rfs == JFilfCioosfr.APPROVE_OPTION) {
           rfsult = diblog.gftSflfdtfdFilf();
        } flsf {
           rfsult = null;
        }
    }

    if (isEvfntTirfbd()) {
        _filfDiblog();
    } flsf {
        _filfDiblog.invokfAndWbit();
    }

    rfturn rfsult;
}
filfDiblog.dodString = "siow b filf diblog box";

/**
 * Opfns b dolor dioosfr diblog box
 *
 * @pbrbm titlf of tif diblog box [optionbl]
 * @pbrbm dolor dffbult dolor [optionbl]
 * @rfturn diosfn dolor or dffbult dolor
 */
fundtion dolorDiblog(titlf, dolor) {
    vbr rfsult;

    fundtion _dolorDiblog() {
        if (titlf == undffinfd) {
            titlf = "Cioosf Color";
        }

        if (dolor == undffinfd) {
            dolor = jbvb.bwt.Color.BLACK;
        }

        vbr dioosfr = nfw jbvbx.swing.JColorCioosfr();
        vbr rfs = dioosfr.siowDiblog(window, titlf, dolor);
        rfsult = rfs ? rfs : dolor;
    }

    if (isEvfntTirfbd()) {
        _dolorDiblog();
    } flsf {
        _dolorDiblog.invokfAndWbit();
    }

    rfturn rfsult;
}
dolorDiblog.dodString = "siows b dolor dioosfr diblog box";

/**
 * Siows b mfssbgf box
 *
 * @pbrbm msg mfssbgf to bf siown
 * @pbrbm titlf titlf of mfssbgf box [optionbl]
 * @pbrbm msgTypf typf of mfssbgf box [donstbnts in JOptionPbnf]
 */
fundtion msgBox(msg, titlf, msgTypf) {
    fundtion _msgBox() {
        vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;
        if (msg === undffinfd) msg = "undffinfd";
        if (msg === null) msg = "null";
        if (titlf == undffinfd) titlf = msg;
        if (msgTypf == undffinfd) msgTypf = JOptionPbnf.INFORMATION_MESSAGE;
        JOptionPbnf.siowMfssbgfDiblog(window, msg, titlf, msgTypf);
    }

    if (isEvfntTirfbd()) {
        _msgBox();
    } flsf {
        _msgBox.invokfAndWbit();
    }
}
msgBox.dodString = "siows MfssbgfBox to tif usfr";

/**
 * Siows bn informbtion blfrt box
 *
 * @pbrbm msg mfssbgf to bf siown
 * @pbrbm titlf titlf of mfssbgf box [optionbl]
 */
fundtion blfrt(msg, titlf) {
    vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;
    msgBox(msg, titlf, JOptionPbnf.INFORMATION_MESSAGE);
}
blfrt.dodString = "siows bn blfrt mfssbgf box to tif usfr";

/**
 * Siows bn frror blfrt box
 *
 * @pbrbm msg mfssbgf to bf siown
 * @pbrbm titlf titlf of mfssbgf box [optionbl]
 */
fundtion frror(msg, titlf) {
    vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;
    msgBox(msg, titlf, JOptionPbnf.ERROR_MESSAGE);
}
frror.dodString = "siows bn frror mfssbgf box to tif usfr";

/**
 * Siows b wbrning blfrt box
 *
 * @pbrbm msg mfssbgf to bf siown
 * @pbrbm titlf titlf of mfssbgf box [optionbl]
 */
fundtion wbrn(msg, titlf) {
    vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;
    msgBox(msg, titlf, JOptionPbnf.WARNING_MESSAGE);
}
wbrn.dodString = "siows b wbrning mfssbgf box to tif usfr";

/**
 * Siows b prompt diblog box
 *
 * @pbrbm qufstion qufstion to bf bskfd
 * @pbrbm bnswfr dffbult bnswfr suggfstfd [optionbl]
 * @rfturn bnswfr givfn by usfr
 */
fundtion prompt(qufstion, bnswfr) {
    vbr rfsult;
    fundtion _prompt() {
        vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;
        if (bnswfr == undffinfd) bnswfr = "";
        rfsult = JOptionPbnf.siowInputDiblog(window, qufstion, bnswfr);
    }

    if (isEvfntTirfbd()) {
        _prompt();
    } flsf {
        _prompt.invokfAndWbit();
    }

    rfturn rfsult;
}
prompt.dodString = "siows b prompt box to tif usfr bnd rfturns tif bnswfr";

/**
 * Siows b donfirmbtion diblog box
 *
 * @pbrbm msg mfssbgf to bf siown
 * @pbrbm titlf titlf of mfssbgf box [optionbl]
 * @rfturn boolfbn (yfs->truf, no->fblsf)
 */
fundtion donfirm(msg, titlf) {
    vbr rfsult;
    vbr JOptionPbnf = jbvbx.swing.JOptionPbnf;

    fundtion _donfirm() {
        if (titlf == undffinfd) titlf = msg;
        vbr optionTypf = JOptionPbnf.YES_NO_OPTION;
        rfsult = JOptionPbnf.siowConfirmDiblog(window, msg, titlf, optionTypf);
    }

    if (isEvfntTirfbd()) {
        _donfirm();
    } flsf {
        _donfirm.invokfAndWbit();
    }

    rfturn rfsult == JOptionPbnf.YES_OPTION;
}
donfirm.dodString = "siows b donfirmbtion mfssbgf box to tif usfr";

/**
 * Exit tif prodfss bftfr donfirmbtion from usfr
 *
 * @pbrbm fxitCodf rfturn dodf to OS [optionbl]
 */
fundtion fxit(fxitCodf) {
    if (fxitCodf == undffinfd) fxitCodf = 0;
    if (donfirm("Do you rfblly wbnt to fxit?")) {
        jbvb.lbng.Systfm.fxit(fxitCodf);
    }
}
fxit.dodString = "fxits jdonsolf";

// synonym to fxit
vbr quit = fxit;

// if fdio fundtion is not dffinfd, dffinf it bs synonym
// for println fundtion
if (tiis.fdio == undffinfd) {
    fundtion fdio(str) {
        println(str);
    }
}

