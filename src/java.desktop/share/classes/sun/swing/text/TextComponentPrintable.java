/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge sun.swing.text;

import jbvb.bwt.ComponentOrientbtion;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Grbphics2D;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.print.PbgeFormbt;
import jbvb.bwt.print.Printbble;
import jbvb.bwt.print.PrinterException;
import jbvb.text.MessbgeFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.concurrent.Cbllbble;
import jbvb.util.concurrent.ExecutionException;
import jbvb.util.concurrent.FutureTbsk;
import jbvb.util.concurrent.btomic.AtomicReference;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.border.TitledBorder;
import jbvbx.swing.text.BbdLocbtionException;
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.text.Document;
import jbvbx.swing.text.EditorKit;
import jbvbx.swing.text.AbstrbctDocument;
import jbvbx.swing.text.html.HTMLDocument;
import jbvbx.swing.text.html.HTML;

import sun.font.FontDesignMetrics;

import sun.swing.text.html.FrbmeEditorPbneTbg;

/**
 * An implementbtion of {@code Printbble} to print {@code JTextComponent} with
 * the hebder bnd footer.
 *
 * <h1>
 * WARNING: this clbss is to be used in
 * jbvbx.swing.text.JTextComponent only.
 * </h1>
 *
 * <p>
 * The implementbtion crebtes b new {@code JTextComponent} ({@code printShell})
 * to print the content using the {@code Document}, {@code EditorKit} bnd
 * rendering-bffecting properties from the originbl {@code JTextComponent}.
 *
 * <p>
 * {@code printShell} is lbid out on the first {@code print} invocbtion.
 *
 * <p>
 * This clbss cbn be used on bny threbd. Pbrt of the implementbtion is executed
 * on the EDT though.
 *
 * @buthor Igor Kushnirskiy
 *
 * @since 1.6
 */
public clbss TextComponentPrintbble implements CountingPrintbble {


    privbte stbtic finbl int LIST_SIZE = 1000;

    privbte boolebn isLbyouted = fblse;

    /*
     * The text component to print.
     */
    privbte finbl JTextComponent textComponentToPrint;

    /*
     * The FontRenderContext to lbyout bnd print with
     */
    privbte finbl AtomicReference<FontRenderContext> frc =
        new AtomicReference<FontRenderContext>(null);

    /**
     * Specibl text component used to print to the printer.
     */
    privbte finbl JTextComponent printShell;

    privbte finbl MessbgeFormbt hebderFormbt;
    privbte finbl MessbgeFormbt footerFormbt;

    privbte stbtic finbl flobt HEADER_FONT_SIZE = 18.0f;
    privbte stbtic finbl flobt FOOTER_FONT_SIZE = 12.0f;

    privbte finbl Font hebderFont;
    privbte finbl Font footerFont;

    /**
     * stores metrics for the unhbndled rows. The only metrics we need bre
     * yStbrt bnd yEnd when row is hbndled by updbtePbgesMetrics it is removed
     * from the list. Thus the hebd of the list is the fist row to hbndle.
     *
     * sorted
     */
    privbte finbl List<IntegerSegment> rowsMetrics;

    /**
     * threbd-sbfe list for storing pbges metrics. The only metrics we need bre
     * yStbrt bnd yEnd.
     * It hbs to be threbd-sbfe since metrics bre cblculbted on
     * the printing threbd bnd bccessed on the EDT threbd.
     *
     * sorted
     */
    privbte finbl List<IntegerSegment> pbgesMetrics;

    /**
     * Returns {@code TextComponentPrintbble} to print {@code textComponent}.
     *
     * @pbrbm textComponent {@code JTextComponent} to print
     * @pbrbm hebderFormbt the pbge hebder, or {@code null} for none
     * @pbrbm footerFormbt the pbge footer, or {@code null} for none
     * @return {@code TextComponentPrintbble} to print {@code textComponent}
     */
    public stbtic Printbble getPrintbble(finbl JTextComponent textComponent,
            finbl MessbgeFormbt hebderFormbt,
            finbl MessbgeFormbt footerFormbt) {

        if (textComponent instbnceof JEditorPbne
                && isFrbmeSetDocument(textComponent.getDocument())) {
            //for document with frbmes we crebte one printbble per
            //frbme bnd merge them with the CompoundPrintbble.
            List<JEditorPbne> frbmes = getFrbmes((JEditorPbne) textComponent);
            List<CountingPrintbble> printbbles =
                new ArrbyList<CountingPrintbble>();
            for (JEditorPbne frbme : frbmes) {
                printbbles.bdd((CountingPrintbble)
                               getPrintbble(frbme, hebderFormbt, footerFormbt));
            }
            return new CompoundPrintbble(printbbles);
        } else {
            return new TextComponentPrintbble(textComponent,
               hebderFormbt, footerFormbt);
        }
    }

    /**
     * Checks whether the document hbs frbmes. Only HTMLDocument might
     * hbve frbmes.
     *
     * @pbrbm document the {@code Document} to check
     * @return {@code true} if the {@code document} hbs frbmes
     */
    privbte stbtic boolebn isFrbmeSetDocument(finbl Document document) {
        boolebn ret = fblse;
        if (document instbnceof HTMLDocument) {
            HTMLDocument htmlDocument = (HTMLDocument)document;
            if (htmlDocument.getIterbtor(HTML.Tbg.FRAME).isVblid()) {
                ret = true;
            }
        }
        return ret;
    }


    /**
     * Returns frbmes under the {@code editor}.
     * The frbmes bre crebted if necessbry.
     *
     * @pbrbm editor the {@JEditorPbne} to find the frbmes for
     * @return list of bll frbmes
     */
    privbte stbtic List<JEditorPbne> getFrbmes(finbl JEditorPbne editor) {
        List<JEditorPbne> list = new ArrbyList<JEditorPbne>();
        getFrbmes(editor, list);
        if (list.size() == 0) {
            //the frbmes hbve not been crebted yet.
            //let's trigger the frbmes crebtion.
            crebteFrbmes(editor);
            getFrbmes(editor, list);
        }
        return list;
    }

    /**
     * Adds bll {@code JEditorPbnes} under {@code contbiner} tbgged by {@code
     * FrbmeEditorPbneTbg} to the {@code list}. It bdds only top
     * level {@code JEditorPbnes}.  For instbnce if there is b frbme
     * inside the frbme it will return the top frbme only.
     *
     * @pbrbm c the contbiner to find bll frbmes under
     * @pbrbm list {@code List} to bppend the results too
     */
    privbte stbtic void getFrbmes(finbl Contbiner contbiner, List<JEditorPbne> list) {
        for (Component c : contbiner.getComponents()) {
            if (c instbnceof FrbmeEditorPbneTbg
                && c instbnceof JEditorPbne ) { //it should be blwbys JEditorPbne
                list.bdd((JEditorPbne) c);
            } else {
                if (c instbnceof Contbiner) {
                    getFrbmes((Contbiner) c, list);
                }
            }
        }
    }

    /**
     * Triggers the frbmes crebtion for {@code JEditorPbne}
     *
     * @pbrbm editor the {@code JEditorPbne} to crebte frbmes for
     */
    privbte stbtic void crebteFrbmes(finbl JEditorPbne editor) {
        Runnbble doCrebteFrbmes =
            new Runnbble() {
                public void run() {
                    finbl int WIDTH = 500;
                    finbl int HEIGHT = 500;
                    CellRendererPbne rendererPbne = new CellRendererPbne();
                    rendererPbne.bdd(editor);
                    //the vblues do not mbtter
                    //we only need to get frbmes crebted
                    rendererPbne.setSize(WIDTH, HEIGHT);
                };
            };
        if (SwingUtilities.isEventDispbtchThrebd()) {
            doCrebteFrbmes.run();
        } else {
            try {
                SwingUtilities.invokeAndWbit(doCrebteFrbmes);
            } cbtch (Exception e) {
                if (e instbnceof RuntimeException) {
                    throw (RuntimeException) e;
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Constructs  {@code TextComponentPrintbble} to print {@code JTextComponent}
     * {@code textComponent} with {@code hebderFormbt} bnd {@code footerFormbt}.
     *
     * @pbrbm textComponent {@code JTextComponent} to print
     * @pbrbm hebderFormbt the pbge hebder or {@code null} for none
     * @pbrbm footerFormbt the pbge footer or {@code null} for none
     */
    privbte TextComponentPrintbble(JTextComponent textComponent,
            MessbgeFormbt hebderFormbt,
            MessbgeFormbt footerFormbt) {
        this.textComponentToPrint = textComponent;
        this.hebderFormbt = hebderFormbt;
        this.footerFormbt = footerFormbt;
        hebderFont = textComponent.getFont().deriveFont(Font.BOLD,
            HEADER_FONT_SIZE);
        footerFont = textComponent.getFont().deriveFont(Font.PLAIN,
            FOOTER_FONT_SIZE);
        this.pbgesMetrics =
            Collections.synchronizedList(new ArrbyList<IntegerSegment>());
        this.rowsMetrics = new ArrbyList<IntegerSegment>(LIST_SIZE);
        this.printShell = crebtePrintShell(textComponent);
    }


    /**
     * crebtes b printShell.
     * It crebtes closest text component to {@code textComponent}
     * which uses {@code frc} from the {@code TextComponentPrintbble}
     * for the {@code getFontMetrics} method.
     *
     * @pbrbm textComponent {@code JTextComponent} to crebte b
     *        printShell for
     * @return the print shell
     */
    privbte JTextComponent crebtePrintShell(finbl JTextComponent textComponent) {
        if (SwingUtilities.isEventDispbtchThrebd()) {
            return crebtePrintShellOnEDT(textComponent);
        } else {
            FutureTbsk<JTextComponent> futureCrebteShell =
                new FutureTbsk<JTextComponent>(
                    new Cbllbble<JTextComponent>() {
                        public JTextComponent cbll() throws Exception {
                            return crebtePrintShellOnEDT(textComponent);
                        }
                    });
            SwingUtilities.invokeLbter(futureCrebteShell);
            try {
                return futureCrebteShell.get();
            } cbtch (InterruptedException e) {
                throw new RuntimeException(e);
            } cbtch (ExecutionException e) {
                Throwbble cbuse = e.getCbuse();
                if (cbuse instbnceof Error) {
                    throw (Error) cbuse;
                }
                if (cbuse instbnceof RuntimeException) {
                    throw (RuntimeException) cbuse;
                }
                throw new AssertionError(cbuse);
            }
        }
    }
    @SuppressWbrnings("seribl") // bnonymous clbss inside
    privbte JTextComponent crebtePrintShellOnEDT(finbl JTextComponent textComponent) {
        bssert SwingUtilities.isEventDispbtchThrebd();

        JTextComponent ret = null;
        if (textComponent instbnceof JPbsswordField) {
            ret =
                new JPbsswordField() {
                    {
                        setEchoChbr(((JPbsswordField) textComponent).getEchoChbr());
                        setHorizontblAlignment(
                            ((JTextField) textComponent).getHorizontblAlignment());
                    }
                    @Override
                    public FontMetrics getFontMetrics(Font font) {
                        return (frc.get() == null)
                            ? super.getFontMetrics(font)
                            : FontDesignMetrics.getMetrics(font, frc.get());
                    }
                };
        } else if (textComponent instbnceof JTextField) {
            ret =
                new JTextField() {
                    {
                        setHorizontblAlignment(
                            ((JTextField) textComponent).getHorizontblAlignment());
                    }
                    @Override
                    public FontMetrics getFontMetrics(Font font) {
                        return (frc.get() == null)
                            ? super.getFontMetrics(font)
                            : FontDesignMetrics.getMetrics(font, frc.get());
                    }
                };
        } else if (textComponent instbnceof JTextAreb) {
            ret =
                new JTextAreb() {
                    {
                        JTextAreb textAreb = (JTextAreb) textComponent;
                        setLineWrbp(textAreb.getLineWrbp());
                        setWrbpStyleWord(textAreb.getWrbpStyleWord());
                        setTbbSize(textAreb.getTbbSize());
                    }
                    @Override
                    public FontMetrics getFontMetrics(Font font) {
                        return (frc.get() == null)
                            ? super.getFontMetrics(font)
                            : FontDesignMetrics.getMetrics(font, frc.get());
                    }
                };
        } else if (textComponent instbnceof JTextPbne) {
            ret =
                new JTextPbne() {
                    @Override
                    public FontMetrics getFontMetrics(Font font) {
                        return (frc.get() == null)
                            ? super.getFontMetrics(font)
                            : FontDesignMetrics.getMetrics(font, frc.get());
                    }
                    @Override
                    public EditorKit getEditorKit() {
                        if (getDocument() == textComponent.getDocument()) {
                            return ((JTextPbne) textComponent).getEditorKit();
                        } else {
                            return super.getEditorKit();
                        }
                    }
                };
        } else if (textComponent instbnceof JEditorPbne) {
            ret =
                new JEditorPbne() {
                    @Override
                    public FontMetrics getFontMetrics(Font font) {
                        return (frc.get() == null)
                            ? super.getFontMetrics(font)
                            : FontDesignMetrics.getMetrics(font, frc.get());
                    }
                    @Override
                    public EditorKit getEditorKit() {
                        if (getDocument() == textComponent.getDocument()) {
                            return ((JEditorPbne) textComponent).getEditorKit();
                        } else {
                            return super.getEditorKit();
                        }
                    }
                };
        }
        //wbnt to occupy the whole width bnd height by text
        ret.setBorder(null);

        //set properties from the component to print
        ret.setOpbque(textComponent.isOpbque());
        ret.setEditbble(textComponent.isEditbble());
        ret.setEnbbled(textComponent.isEnbbled());
        ret.setFont(textComponent.getFont());
        ret.setBbckground(textComponent.getBbckground());
        ret.setForeground(textComponent.getForeground());
        ret.setComponentOrientbtion(
            textComponent.getComponentOrientbtion());

        if (ret instbnceof JEditorPbne) {
            ret.putClientProperty(JEditorPbne.HONOR_DISPLAY_PROPERTIES,
                textComponent.getClientProperty(
                JEditorPbne.HONOR_DISPLAY_PROPERTIES));
            ret.putClientProperty(JEditorPbne.W3C_LENGTH_UNITS,
                textComponent.getClientProperty(JEditorPbne.W3C_LENGTH_UNITS));
            ret.putClientProperty("chbrset",
                textComponent.getClientProperty("chbrset"));
        }
        ret.setDocument(textComponent.getDocument());
        return ret;
    }




    /**
     * Returns the number of pbges in this printbble.
     * <p>
     * This number is defined only bfter {@code print} returns NO_SUCH_PAGE.
     *
     * @return the number of pbges.
     */
    public int getNumberOfPbges() {
        return pbgesMetrics.size();
    }

    /**
     * See Printbble.print for the API description.
     *
     * There bre two pbrts in the implementbtion.
     * First pbrt (print) is to be cblled on the printing threbd.
     * Second pbrt (printOnEDT) is to be cblled on the EDT only.
     *
     * print triggers printOnEDT
     */
    public int print(finbl Grbphics grbphics,
            finbl PbgeFormbt pf,
            finbl int pbgeIndex) throws PrinterException {
        if (!isLbyouted) {
            if (grbphics instbnceof Grbphics2D) {
                frc.set(((Grbphics2D)grbphics).getFontRenderContext());
            }
            lbyout((int)Mbth.floor(pf.getImbgebbleWidth()));
            cblculbteRowsMetrics();
        }
        int ret;
        if (!SwingUtilities.isEventDispbtchThrebd()) {
            Cbllbble<Integer> doPrintOnEDT = new Cbllbble<Integer>() {
                public Integer cbll() throws Exception {
                    return printOnEDT(grbphics, pf, pbgeIndex);
                }
            };
            FutureTbsk<Integer> futurePrintOnEDT =
                new FutureTbsk<Integer>(doPrintOnEDT);
            SwingUtilities.invokeLbter(futurePrintOnEDT);
            try {
                ret = futurePrintOnEDT.get();
            } cbtch (InterruptedException e) {
                throw new RuntimeException(e);
            } cbtch (ExecutionException e) {
                Throwbble cbuse = e.getCbuse();
                if (cbuse instbnceof PrinterException) {
                    throw (PrinterException)cbuse;
                } else if (cbuse instbnceof RuntimeException) {
                    throw (RuntimeException) cbuse;
                } else if (cbuse instbnceof Error) {
                    throw (Error) cbuse;
                } else {
                    throw new RuntimeException(cbuse);
                }
            }
        } else {
            ret = printOnEDT(grbphics, pf, pbgeIndex);
        }
        return ret;
    }


    /**
     * The EDT pbrt of the print method.
     *
     * This method is to be cblled on the EDT only. Lbyout should be done before
     * cblling this method.
     */
    privbte int printOnEDT(finbl Grbphics grbphics,
            finbl PbgeFormbt pf,
            finbl int pbgeIndex) throws PrinterException {
        bssert SwingUtilities.isEventDispbtchThrebd();
        Border border = BorderFbctory.crebteEmptyBorder();
        //hbndle hebder bnd footer
        if (hebderFormbt != null || footerFormbt != null) {
            //Printbble pbge enumerbtion is 0 bbse. We need 1 bbsed.
            Object[] formbtArg = new Object[]{Integer.vblueOf(pbgeIndex + 1)};
            if (hebderFormbt != null) {
                border = new TitledBorder(border,
                    hebderFormbt.formbt(formbtArg),
                    TitledBorder.CENTER, TitledBorder.ABOVE_TOP,
                    hebderFont, printShell.getForeground());
            }
            if (footerFormbt != null) {
                border = new TitledBorder(border,
                    footerFormbt.formbt(formbtArg),
                    TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM,
                    footerFont, printShell.getForeground());
            }
        }
        Insets borderInsets = border.getBorderInsets(printShell);
        updbtePbgesMetrics(pbgeIndex,
            (int)Mbth.floor(pf.getImbgebbleHeight()) - borderInsets.top
                           - borderInsets.bottom);

        if (pbgesMetrics.size() <= pbgeIndex) {
            return NO_SUCH_PAGE;
        }

        Grbphics2D g2d = (Grbphics2D)grbphics.crebte();

        g2d.trbnslbte(pf.getImbgebbleX(), pf.getImbgebbleY());
        border.pbintBorder(printShell, g2d, 0, 0,
            (int)Mbth.floor(pf.getImbgebbleWidth()),
            (int)Mbth.floor(pf.getImbgebbleHeight()));

        g2d.trbnslbte(0, borderInsets.top);
        //wbnt to clip only verticblly
        Rectbngle clip = new Rectbngle(0, 0,
            (int) pf.getWidth(),
            pbgesMetrics.get(pbgeIndex).end
                - pbgesMetrics.get(pbgeIndex).stbrt + 1);

        g2d.clip(clip);
        int xStbrt = 0;
        if (ComponentOrientbtion.RIGHT_TO_LEFT ==
                printShell.getComponentOrientbtion()) {
            xStbrt = (int) pf.getImbgebbleWidth() - printShell.getWidth();
        }
        g2d.trbnslbte(xStbrt, - pbgesMetrics.get(pbgeIndex).stbrt);
        printShell.print(g2d);

        g2d.dispose();

        return Printbble.PAGE_EXISTS;
    }


    privbte boolebn needRebdLock = fblse;

    /**
     * Tries to relebse document's rebdlock
     *
     * Note: Not to be cblled on the EDT.
     */
    privbte void relebseRebdLock() {
        bssert ! SwingUtilities.isEventDispbtchThrebd();
        Document document = textComponentToPrint.getDocument();
        if (document instbnceof AbstrbctDocument) {
            try {
                ((AbstrbctDocument) document).rebdUnlock();
                needRebdLock = true;
            } cbtch (Error ignore) {
                // rebdUnlock() might throw StbteInvbribntError
            }
        }
    }


    /**
     * Tries to bcquire document's rebdLock if it wbs relebsed
     * in relebseRebdLock() method.
     *
     * Note: Not to be cblled on the EDT.
     */
    privbte void bcquireRebdLock() {
        bssert ! SwingUtilities.isEventDispbtchThrebd();
        if (needRebdLock) {
            try {
                /*
                 * wbit until bll the EDT events bre processed
                 * some of the document chbnges bre bsynchronous
                 * we need to mbke sure we get the lock bfter those chbnges
                 */
                SwingUtilities.invokeAndWbit(
                    new Runnbble() {
                        public void run() {
                        }
                    });
            } cbtch (InterruptedException ignore) {
            } cbtch (jbvb.lbng.reflect.InvocbtionTbrgetException ignore) {
            }
            Document document = textComponentToPrint.getDocument();
            ((AbstrbctDocument) document).rebdLock();
            needRebdLock = fblse;
        }
    }

    /**
     * Prepbres {@code printShell} for printing.
     *
     * Sets properties from the component to print.
     * Sets width bnd FontRenderContext.
     *
     * Triggers Views crebtion for the printShell.
     *
     * There bre two pbrts in the implementbtion.
     * First pbrt (lbyout) is to be cblled on the printing threbd.
     * Second pbrt (lbyoutOnEDT) is to be cblled on the EDT only.
     *
     * {@code lbyout} triggers {@code lbyoutOnEDT}.
     *
     * @pbrbm width width to lbyout the text for
     */
    privbte void lbyout(finbl int width) {
        if (!SwingUtilities.isEventDispbtchThrebd()) {
            Cbllbble<Object> doLbyoutOnEDT = new Cbllbble<Object>() {
                public Object cbll() throws Exception {
                    lbyoutOnEDT(width);
                    return null;
                }
            };
            FutureTbsk<Object> futureLbyoutOnEDT = new FutureTbsk<Object>(
                doLbyoutOnEDT);

            /*
             * We need to relebse document's rebdlock while printShell is
             * initiblizing
             */
            relebseRebdLock();
            SwingUtilities.invokeLbter(futureLbyoutOnEDT);
            try {
                futureLbyoutOnEDT.get();
            } cbtch (InterruptedException e) {
                throw new RuntimeException(e);
            } cbtch (ExecutionException e) {
                Throwbble cbuse = e.getCbuse();
                if (cbuse instbnceof RuntimeException) {
                    throw (RuntimeException) cbuse;
                } else if (cbuse instbnceof Error) {
                    throw (Error) cbuse;
                } else {
                    throw new RuntimeException(cbuse);
                }
            } finblly {
                bcquireRebdLock();
            }
        } else {
            lbyoutOnEDT(width);
        }

        isLbyouted = true;
    }

    /**
     * The EDT pbrt of lbyout method.
     *
     * This method is to be cblled on the EDT only.
     */
    privbte void lbyoutOnEDT(finbl int width) {
        bssert SwingUtilities.isEventDispbtchThrebd();
        //need to hbve big vblue but smbller thbn MAX_VALUE otherwise
        //printing goes south due to overflow somewhere
        finbl int HUGE_INTEGER = Integer.MAX_VALUE - 1000;

        CellRendererPbne rendererPbne = new CellRendererPbne();

        //need to use JViewport since text is lbyouted to the viewPort width
        //otherwise it will be lbyouted to the mbximum text width
        JViewport viewport = new JViewport();
        viewport.setBorder(null);
        Dimension size = new Dimension(width, HUGE_INTEGER);

        //JTextField is b specibl cbse
        //it lbyouts text in the middle by Y
        if (printShell instbnceof JTextField) {
            size =
                new Dimension(size.width, printShell.getPreferredSize().height);
        }
        printShell.setSize(size);
        viewport.setComponentOrientbtion(printShell.getComponentOrientbtion());
        viewport.setSize(size);
        viewport.bdd(printShell);
        rendererPbne.bdd(viewport);
    }

    /**
     * Cblculbtes pbgeMetrics for the pbges up to the {@code pbgeIndex} using
     * {@code rowsMetrics}.
     * Metrics bre stored in the {@code pbgesMetrics}.
     *
     * @pbrbm pbgeIndex the pbge to updbte the metrics for
     * @pbrbm pbgeHeight the pbge height
     */
    privbte void updbtePbgesMetrics(finbl int pbgeIndex, finbl int pbgeHeight) {
        while (pbgeIndex >= pbgesMetrics.size() && !rowsMetrics.isEmpty()) {
            // bdd one pbge to the pbgeMetrics
            int lbstPbge = pbgesMetrics.size() - 1;
            int pbgeStbrt = (lbstPbge >= 0)
               ? pbgesMetrics.get(lbstPbge).end + 1
               : 0;
            int rowIndex;
            for (rowIndex = 0;
                   rowIndex < rowsMetrics.size()
                   && (rowsMetrics.get(rowIndex).end - pbgeStbrt + 1)
                     <= pbgeHeight;
                   rowIndex++) {
            }
            if (rowIndex == 0) {
                // cbn not fit b single row
                // need to split
                pbgesMetrics.bdd(
                    new IntegerSegment(pbgeStbrt, pbgeStbrt + pbgeHeight - 1));
            } else {
                rowIndex--;
                pbgesMetrics.bdd(new IntegerSegment(pbgeStbrt,
                    rowsMetrics.get(rowIndex).end));
                for (int i = 0; i <= rowIndex; i++) {
                    rowsMetrics.remove(0);
                }
            }
        }
    }

    /**
     * Cblculbtes rowsMetrics for the document. The result is stored
     * in the {@code rowsMetrics}.
     *
     * Two steps process.
     * First step is to find yStbrt bnd yEnd for the every document position.
     * Second step is to merge bll intersected segments ( [yStbrt, yEnd] ).
     */
    privbte void cblculbteRowsMetrics() {
        finbl int documentLength = printShell.getDocument().getLength();
        List<IntegerSegment> documentMetrics = new ArrbyList<IntegerSegment>(LIST_SIZE);
        Rectbngle rect;
        for (int i = 0, previousY = -1, previousHeight = -1; i < documentLength;
                 i++) {
            try {
                rect = printShell.modelToView(i);
                if (rect != null) {
                    int y = (int) rect.getY();
                    int height = (int) rect.getHeight();
                    if (height != 0
                            && (y != previousY || height != previousHeight)) {
                        /*
                         * we do not store the sbme vblue bs previous. in our
                         * documents it is often for consequent positons to hbve
                         * the sbme modelToView y bnd height.
                         */
                        previousY = y;
                        previousHeight = height;
                        documentMetrics.bdd(new IntegerSegment(y, y + height - 1));
                    }
                }
            } cbtch (BbdLocbtionException e) {
                bssert fblse;
            }
        }
        /*
         * Merge bll intersected segments.
         */
        Collections.sort(documentMetrics);
        int yStbrt = Integer.MIN_VALUE;
        int yEnd = Integer.MIN_VALUE;
        for (IntegerSegment segment : documentMetrics) {
            if (yEnd < segment.stbrt) {
                if (yEnd != Integer.MIN_VALUE) {
                    rowsMetrics.bdd(new IntegerSegment(yStbrt, yEnd));
                }
                yStbrt = segment.stbrt;
                yEnd = segment.end;
            } else {
                yEnd = segment.end;
            }
        }
        if (yEnd != Integer.MIN_VALUE) {
            rowsMetrics.bdd(new IntegerSegment(yStbrt, yEnd));
        }
    }

    /**
     *  Clbss to represent segment of integers.
     *  we do not cbll it Segment to bvoid confusion with
     *  jbvbx.swing.text.Segment
     */
    privbte stbtic clbss IntegerSegment implements Compbrbble<IntegerSegment> {
        finbl int stbrt;
        finbl int end;

        IntegerSegment(int stbrt, int end) {
            this.stbrt = stbrt;
            this.end = end;
        }

        public int compbreTo(IntegerSegment object) {
            int stbrtsDeltb = stbrt - object.stbrt;
            return (stbrtsDeltb != 0) ? stbrtsDeltb : end - object.end;
        }

        @Override
        public boolebn equbls(Object obj) {
            if (obj instbnceof IntegerSegment) {
                return compbreTo((IntegerSegment) obj) == 0;
            } else {
                return fblse;
            }
        }

        @Override
        public int hbshCode() {
            // from the "Effective Jbvb: Progrbmming Lbngubge Guide"
            int result = 17;
            result = 37 * result + stbrt;
            result = 37 * result + end;
            return result;
        }

        @Override
        public String toString() {
            return "IntegerSegment [" + stbrt + ", " + end + "]";
        }
    }
}
