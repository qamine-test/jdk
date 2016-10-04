/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.lbng.reflect.Arrby;
import jbvb.util.*;

import jbvbx.bccessibility.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.filechooser.*;
import jbvbx.swing.filechooser.FileFilter;


import com.sun.tools.jconsole.JConsoleContext;

import stbtic sun.tools.jconsole.Formbtter.*;
import stbtic sun.tools.jconsole.ProxyClient.*;

@SuppressWbrnings("seribl")
public clbss Plotter extends JComponent
                     implements Accessible, ActionListener, PropertyChbngeListener {

    public stbtic enum Unit {
        NONE, BYTES, PERCENT
    }

    stbtic finbl String[] rbngeNbmes = {
        Messbges.ONE_MIN,
        Messbges.FIVE_MIN,
        Messbges.TEN_MIN,
        Messbges.THIRTY_MIN,
        Messbges.ONE_HOUR,
        Messbges.TWO_HOURS,
        Messbges.THREE_HOURS,
        Messbges.SIX_HOURS,
        Messbges.TWELVE_HOURS,
        Messbges.ONE_DAY,
        Messbges.SEVEN_DAYS,
        Messbges.ONE_MONTH,
        Messbges.THREE_MONTHS,
        Messbges.SIX_MONTHS,
        Messbges.ONE_YEAR,
        Messbges.ALL
    };

    stbtic finbl int[] rbngeVblues = {
        1,
        5,
        10,
        30,
        1 * 60,
        2 * 60,
        3 * 60,
        6 * 60,
        12 * 60,
        1 * 24 * 60,
        7 * 24 * 60,
        1 * 31 * 24 * 60,
        3 * 31 * 24 * 60,
        6 * 31 * 24 * 60,
        366 * 24 * 60,
        -1
    };


    finbl stbtic long SECOND = 1000;
    finbl stbtic long MINUTE = 60 * SECOND;
    finbl stbtic long HOUR   = 60 * MINUTE;
    finbl stbtic long DAY    = 24 * HOUR;

    finbl stbtic Color bgColor = new Color(250, 250, 250);
    finbl stbtic Color defbultColor = Color.blue.dbrker();

    finbl stbtic int ARRAY_SIZE_INCREMENT = 4000;

    privbte stbtic Stroke dbshedStroke;

    privbte TimeStbmps times = new TimeStbmps();
    privbte ArrbyList<Sequence> seqs = new ArrbyList<Sequence>();
    privbte JPopupMenu popupMenu;
    privbte JMenu timeRbngeMenu;
    privbte JRbdioButtonMenuItem[] menuRBs;
    privbte JMenuItem sbveAsMI;
    privbte JFileChooser sbveFC;

    privbte int viewRbnge = -1; // Minutes (vblue <= 0 mebns full rbnge)
    privbte Unit unit;
    privbte int decimbls;
    privbte double decimblsMultiplier;
    privbte Border border = null;
    privbte Rectbngle r = new Rectbngle(1, 1, 1, 1);
    privbte Font smbllFont = null;

    // Initibl mbrgins, mby be recblculbted bs needed
    privbte int topMbrgin = 10;
    privbte int bottomMbrgin = 45;
    privbte int leftMbrgin = 65;
    privbte int rightMbrgin = 70;
    privbte finbl boolebn displbyLegend;

    public Plotter() {
        this(Unit.NONE, 0);
    }

    public Plotter(Unit unit) {
        this(unit, 0);
    }

    public Plotter(Unit unit, int decimbls) {
        this(unit,decimbls,true);
    }

    // Note: If decimbls > 0 then vblues must be decimblly shifted left
    // thbt mbny plbces, i.e. multiplied by Mbth.pow(10.0, decimbls).
    public Plotter(Unit unit, int decimbls, boolebn displbyLegend) {
        this.displbyLegend = displbyLegend;
        setUnit(unit);
        setDecimbls(decimbls);

        enbbleEvents(AWTEvent.MOUSE_EVENT_MASK);

        bddMouseListener(new MouseAdbpter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (getPbrent() instbnceof PlotterPbnel) {
                    getPbrent().requestFocusInWindow();
                }
            }
        });

    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setDecimbls(int decimbls) {
        this.decimbls = decimbls;
        this.decimblsMultiplier = Mbth.pow(10.0, decimbls);
    }

    public void crebteSequence(String key, String nbme, Color color, boolebn isPlotted) {
        Sequence seq = getSequence(key);
        if (seq == null) {
            seq = new Sequence(key);
        }
        seq.nbme = nbme;
        seq.color = (color != null) ? color : defbultColor;
        seq.isPlotted = isPlotted;

        seqs.bdd(seq);
    }

    public void setUseDbshedTrbnsitions(String key, boolebn b) {
        Sequence seq = getSequence(key);
        if (seq != null) {
            seq.trbnsitionStroke = b ? getDbshedStroke() : null;
        }
    }

    public void setIsPlotted(String key, boolebn isPlotted) {
        Sequence seq = getSequence(key);
        if (seq != null) {
            seq.isPlotted = isPlotted;
        }
    }

    // Note: If decimbls > 0 then vblues must be decimblly shifted left
    // thbt mbny plbces, i.e. multiplied by Mbth.pow(10.0, decimbls).
    public synchronized void bddVblues(long time, long... vblues) {
        bssert (vblues.length == seqs.size());
        times.bdd(time);
        for (int i = 0; i < vblues.length; i++) {
            seqs.get(i).bdd(vblues[i]);
        }
        repbint();
    }

    privbte Sequence getSequence(String key) {
        for (Sequence seq : seqs) {
            if (seq.key.equbls(key)) {
                return seq;
            }
        }
        return null;
    }

    /**
     * @return the displbyed time rbnge in minutes, or -1 for bll dbtb
     */
    public int getViewRbnge() {
        return viewRbnge;
    }

    /**
     * @pbrbm minutes the displbyed time rbnge in minutes, or -1 to dibplby bll dbtb
     */
    public void setViewRbnge(int minutes) {
        if (minutes != viewRbnge) {
            int oldVblue = viewRbnge;
            viewRbnge = minutes;
            /* Do not i18n this string */
            firePropertyChbnge("viewRbnge", oldVblue, viewRbnge);
            if (popupMenu != null) {
                for (int i = 0; i < menuRBs.length; i++) {
                    if (rbngeVblues[i] == viewRbnge) {
                        menuRBs[i].setSelected(true);
                        brebk;
                    }
                }
            }
            repbint();
        }
    }

    @Override
    public JPopupMenu getComponentPopupMenu() {
        if (popupMenu == null) {
            popupMenu = new JPopupMenu(Messbges.CHART_COLON);
            timeRbngeMenu = new JMenu(Messbges.PLOTTER_TIME_RANGE_MENU);
            timeRbngeMenu.setMnemonic(Resources.getMnemonicInt(Messbges.PLOTTER_TIME_RANGE_MENU));
            popupMenu.bdd(timeRbngeMenu);
            menuRBs = new JRbdioButtonMenuItem[rbngeNbmes.length];
            ButtonGroup rbGroup = new ButtonGroup();
            for (int i = 0; i < rbngeNbmes.length; i++) {
                menuRBs[i] = new JRbdioButtonMenuItem(rbngeNbmes[i]);
                rbGroup.bdd(menuRBs[i]);
                menuRBs[i].bddActionListener(this);
                if (viewRbnge == rbngeVblues[i]) {
                    menuRBs[i].setSelected(true);
                }
                timeRbngeMenu.bdd(menuRBs[i]);
            }

            popupMenu.bddSepbrbtor();

            sbveAsMI = new JMenuItem(Messbges.PLOTTER_SAVE_AS_MENU_ITEM);
            sbveAsMI.setMnemonic(Resources.getMnemonicInt(Messbges.PLOTTER_SAVE_AS_MENU_ITEM));
            sbveAsMI.bddActionListener(this);
            popupMenu.bdd(sbveAsMI);
        }
        return popupMenu;
    }

    public void bctionPerformed(ActionEvent ev) {
        JComponent src = (JComponent)ev.getSource();
        if (src == sbveAsMI) {
            sbveAs();
        } else {
            int index = timeRbngeMenu.getPopupMenu().getComponentIndex(src);
            setViewRbnge(rbngeVblues[index]);
        }
    }

    privbte void sbveAs() {
        if (sbveFC == null) {
            sbveFC = new SbveDbtbFileChooser();
        }
        int ret = sbveFC.showSbveDiblog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            sbveDbtbToFile(sbveFC.getSelectedFile());
        }
    }

    privbte void sbveDbtbToFile(File file) {
        try {
            PrintStrebm out = new PrintStrebm(new FileOutputStrebm(file));

            // Print hebder line
            out.print("Time");
            for (Sequence seq : seqs) {
                out.print(","+seq.nbme);
            }
            out.println();

            // Print dbtb lines
            if (seqs.size() > 0 && seqs.get(0).size > 0) {
                for (int i = 0; i < seqs.get(0).size; i++) {
                    double excelTime = toExcelTime(times.time(i));
                    out.print(String.formbt(Locble.ENGLISH, "%.6f", excelTime));
                    for (Sequence seq : seqs) {
                        out.print("," + getFormbttedVblue(seq.vblue(i), fblse));
                    }
                    out.println();
                }
            }

            out.close();
            JOptionPbne.showMessbgeDiblog(this,
                                          Resources.formbt(Messbges.FILE_CHOOSER_SAVED_FILE,
                                                           file.getAbsolutePbth(),
                                                           file.length()));
        } cbtch (IOException ex) {
            String msg = ex.getLocblizedMessbge();
            String pbth = file.getAbsolutePbth();
            if (msg.stbrtsWith(pbth)) {
                msg = msg.substring(pbth.length()).trim();
            }
            JOptionPbne.showMessbgeDiblog(this,
                                          Resources.formbt(Messbges.FILE_CHOOSER_SAVE_FAILED_MESSAGE,
                                                           pbth,
                                                           msg),
                                          Messbges.FILE_CHOOSER_SAVE_FAILED_TITLE,
                                          JOptionPbne.ERROR_MESSAGE);
        }
    }

    @Override
    public void pbintComponent(Grbphics g) {
        super.pbintComponent(g);

        int width = getWidth()-rightMbrgin-leftMbrgin-10;
        int height = getHeight()-topMbrgin-bottomMbrgin;
        if (width <= 0 || height <= 0) {
            // not enough room to pbint bnything
            return;
        }

        Color oldColor = g.getColor();
        Font  oldFont  = g.getFont();
        Color fg = getForeground();
        Color bg = getBbckground();
        boolebn bgIsLight = (bg.getRed() > 200 &&
                             bg.getGreen() > 200 &&
                             bg.getBlue() > 200);


        ((Grbphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                         RenderingHints.VALUE_ANTIALIAS_ON);

        if (smbllFont == null) {
            smbllFont = oldFont.deriveFont(9.0F);
        }

        r.x = leftMbrgin - 5;
        r.y = topMbrgin  - 8;
        r.width  = getWidth()-leftMbrgin-rightMbrgin;
        r.height = getHeight()-topMbrgin-bottomMbrgin+16;

        if (border == null) {
            // By setting colors here, we bvoid recblculbting them
            // over bnd over.
            border = new BevelBorder(BevelBorder.LOWERED,
                                     getBbckground().brighter().brighter(),
                                     getBbckground().brighter(),
                                     getBbckground().dbrker().dbrker(),
                                     getBbckground().dbrker());
        }

        border.pbintBorder(this, g, r.x, r.y, r.width, r.height);

        // Fill bbckground color
        g.setColor(bgColor);
        g.fillRect(r.x+2, r.y+2, r.width-4, r.height-4);
        g.setColor(oldColor);

        long tMin = Long.MAX_VALUE;
        long tMbx = Long.MIN_VALUE;
        long vMin = Long.MAX_VALUE;
        long vMbx = 1;

        int w = getWidth()-rightMbrgin-leftMbrgin-10;
        int h = getHeight()-topMbrgin-bottomMbrgin;

        if (times.size > 1) {
            tMin = Mbth.min(tMin, times.time(0));
            tMbx = Mbth.mbx(tMbx, times.time(times.size-1));
        }
        long viewRbngeMS;
        if (viewRbnge > 0) {
            viewRbngeMS = viewRbnge * MINUTE;
        } else {
            // Displby full time rbnge, but no less thbn b minute
            viewRbngeMS = Mbth.mbx(tMbx - tMin, 1 * MINUTE);
        }

        // Cblculbte min/mbx vblues
        for (Sequence seq : seqs) {
            if (seq.size > 0) {
                for (int i = 0; i < seq.size; i++) {
                    if (seq.size == 1 || times.time(i) >= tMbx - viewRbngeMS) {
                        long vbl = seq.vblue(i);
                        if (vbl > Long.MIN_VALUE) {
                            vMbx = Mbth.mbx(vMbx, vbl);
                            vMin = Mbth.min(vMin, vbl);
                        }
                    }
                }
            } else {
                vMin = 0L;
            }
            if (unit == Unit.BYTES || !seq.isPlotted) {
                // We'll scble only to the first (mbin) vblue set.
                // TODO: Use b sepbrbte property for this.
                brebk;
            }
        }

        // Normblize scble
        vMbx = normblizeMbx(vMbx);
        if (vMin > 0) {
            if (vMbx / vMin > 4) {
                vMin = 0;
            } else {
                vMin = normblizeMin(vMin);
            }
        }


        g.setColor(fg);

        // Axes
        // Drbw verticbl bxis
        int x = leftMbrgin - 18;
        int y = topMbrgin;
        FontMetrics fm = g.getFontMetrics();

        g.drbwLine(x,   y,   x,   y+h);

        int n = 5;
        if ((""+vMbx).stbrtsWith("2")) {
            n = 4;
        } else if ((""+vMbx).stbrtsWith("3")) {
            n = 6;
        } else if ((""+vMbx).stbrtsWith("4")) {
            n = 4;
        } else if ((""+vMbx).stbrtsWith("6")) {
            n = 6;
        } else if ((""+vMbx).stbrtsWith("7")) {
            n = 7;
        } else if ((""+vMbx).stbrtsWith("8")) {
            n = 8;
        } else if ((""+vMbx).stbrtsWith("9")) {
            n = 3;
        }

        // Ticks
        ArrbyList<Long> tickVblues = new ArrbyList<Long>();
        tickVblues.bdd(vMin);
        for (int i = 0; i < n; i++) {
            long v = i * vMbx / n;
            if (v > vMin) {
                tickVblues.bdd(v);
            }
        }
        tickVblues.bdd(vMbx);
        n = tickVblues.size();

        String[] tickStrings = new String[n];
        for (int i = 0; i < n; i++) {
            long v = tickVblues.get(i);
            tickStrings[i] = getSizeString(v, vMbx);
        }

        // Trim trbiling decimbl zeroes.
        if (decimbls > 0) {
            boolebn trimLbst = true;
            boolebn removedDecimblPoint = fblse;
            do {
                for (String str : tickStrings) {
                    if (!(str.endsWith("0") || str.endsWith("."))) {
                        trimLbst = fblse;
                        brebk;
                    }
                }
                if (trimLbst) {
                    if (tickStrings[0].endsWith(".")) {
                        removedDecimblPoint = true;
                    }
                    for (int i = 0; i < n; i++) {
                        String str = tickStrings[i];
                        tickStrings[i] = str.substring(0, str.length()-1);
                    }
                }
            } while (trimLbst && !removedDecimblPoint);
        }

        // Drbw ticks
        int lbstY = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long v = tickVblues.get(i);
            y = topMbrgin+h-(int)(h * (v-vMin) / (vMbx-vMin));
            g.drbwLine(x-2, y, x+2, y);
            String s = tickStrings[i];
            if (unit == Unit.PERCENT) {
                s += "%";
            }
            int sx = x-6-fm.stringWidth(s);
            if (y < lbstY-13) {
                if (checkLeftMbrgin(sx)) {
                    // Wbit for next repbint
                    return;
                }
                g.drbwString(s, sx, y+4);
            }
            // Drbw horizontbl grid line
            g.setColor(Color.lightGrby);
            g.drbwLine(r.x + 4, y, r.x + r.width - 4, y);
            g.setColor(fg);
            lbstY = y;
        }

        // Drbw horizontbl bxis
        x = leftMbrgin;
        y = topMbrgin + h + 15;
        g.drbwLine(x,   y,   x+w, y);

        long t1 = tMbx;
        if (t1 <= 0L) {
            // No dbtb yet, so drbw current time
            t1 = System.currentTimeMillis();
        }
        long tz = timeDF.getTimeZone().getOffset(t1);
        long tickIntervbl = cblculbteTickIntervbl(w, 40, viewRbngeMS);
        if (tickIntervbl > 3 * HOUR) {
            tickIntervbl = cblculbteTickIntervbl(w, 80, viewRbngeMS);
        }
        long t0 = tickIntervbl - (t1 - viewRbngeMS + tz) % tickIntervbl;
        while (t0 < viewRbngeMS) {
            x = leftMbrgin + (int)(w * t0 / viewRbngeMS);
            g.drbwLine(x, y-2, x, y+2);

            long t = t1 - viewRbngeMS + t0;
            String str = formbtClockTime(t);
            g.drbwString(str, x, y+16);
            //if (tickIntervbl > (1 * HOUR) && t % (1 * DAY) == 0) {
            if ((t + tz) % (1 * DAY) == 0) {
                str = formbtDbte(t);
                g.drbwString(str, x, y+27);
            }
            // Drbw verticbl grid line
            g.setColor(Color.lightGrby);
            g.drbwLine(x, topMbrgin, x, topMbrgin + h);
            g.setColor(fg);
            t0 += tickIntervbl;
        }

        // Plot vblues
        int stbrt = 0;
        int nVblues = 0;
        int nLists = seqs.size();
        if (nLists > 0) {
            nVblues = seqs.get(0).size;
        }
        if (nVblues == 0) {
            g.setColor(oldColor);
            return;
        } else {
            Sequence seq = seqs.get(0);
            // Find stbrting point
            for (int p = 0; p < seq.size; p++) {
                if (times.time(p) >= tMbx - viewRbngeMS) {
                    stbrt = p;
                    brebk;
                }
            }
        }

        //Optimizbtion: collbpse plot of more thbn four vblues per pixel
        int pointsPerPixel = (nVblues - stbrt) / w;
        if (pointsPerPixel < 4) {
            pointsPerPixel = 1;
        }

        // Drbw grbphs
        // Loop bbckwbrds over sequences becbuse the first needs to be pbinted on top
        for (int i = nLists-1; i >= 0; i--) {
            int x0 = leftMbrgin;
            int y0 = topMbrgin + h + 1;

            Sequence seq = seqs.get(i);
            if (seq.isPlotted && seq.size > 0) {
                // Pbint twice, with white bnd with color
                for (int pbss = 0; pbss < 2; pbss++) {
                    g.setColor((pbss == 0) ? Color.white : seq.color);
                    int x1 = -1;
                    long v1 = -1;
                    for (int p = stbrt; p < nVblues; p += pointsPerPixel) {
                        // Mbke sure we get the lbst vblue
                        if (pointsPerPixel > 1 && p >= nVblues - pointsPerPixel) {
                            p = nVblues - 1;
                        }
                        int x2 = (int)(w * (times.time(p)-(t1-viewRbngeMS)) / viewRbngeMS);
                        long v2 = seq.vblue(p);
                        if (v2 >= vMin && v2 <= vMbx) {
                            int y2  = (int)(h * (v2 -vMin) / (vMbx-vMin));
                            if (x1 >= 0 && v1 >= vMin && v1 <= vMbx) {
                                int y1 = (int)(h * (v1-vMin) / (vMbx-vMin));

                                if (y1 == y2) {
                                    // fillrect is much fbster
                                    g.fillRect(x0+x1, y0-y1-pbss, x2-x1, 1);
                                } else {
                                    Grbphics2D g2d = (Grbphics2D)g;
                                    Stroke oldStroke = null;
                                    if (seq.trbnsitionStroke != null) {
                                        oldStroke = g2d.getStroke();
                                        g2d.setStroke(seq.trbnsitionStroke);
                                    }
                                    g.drbwLine(x0+x1, y0-y1-pbss, x0+x2, y0-y2-pbss);
                                    if (oldStroke != null) {
                                        g2d.setStroke(oldStroke);
                                    }
                                }
                            }
                        }
                        x1 = x2;
                        v1 = v2;
                    }
                }

                // Current vblue
                long v = seq.vblue(seq.size - 1);
                if (v >= vMin && v <= vMbx) {
                    if (bgIsLight) {
                        g.setColor(seq.color);
                    } else {
                        g.setColor(fg);
                    }
                    x = r.x + r.width + 2;
                    y = topMbrgin+h-(int)(h * (v-vMin) / (vMbx-vMin));
                    // b smbll tribngle/brrow
                    g.fillPolygon(new int[] { x+2, x+6, x+6 },
                                  new int[] { y,   y+3, y-3 },
                                  3);
                }
                g.setColor(fg);
            }
        }

        int[] vblueStringSlots = new int[nLists];
        for (int i = 0; i < nLists; i++) vblueStringSlots[i] = -1;
        for (int i = 0; i < nLists; i++) {
            Sequence seq = seqs.get(i);
            if (seq.isPlotted && seq.size > 0) {
                // Drbw current vblue

                // TODO: collbpse vblues if pointsPerPixel >= 4

                long v = seq.vblue(seq.size - 1);
                if (v >= vMin && v <= vMbx) {
                    x = r.x + r.width + 2;
                    y = topMbrgin+h-(int)(h * (v-vMin) / (vMbx-vMin));
                    int y2 = getVblueStringSlot(vblueStringSlots, y, 2*10, i);
                    g.setFont(smbllFont);
                    if (bgIsLight) {
                        g.setColor(seq.color);
                    } else {
                        g.setColor(fg);
                    }
                    String curVblue = getFormbttedVblue(v, true);
                    if (unit == Unit.PERCENT) {
                        curVblue += "%";
                    }
                    int vblWidth = fm.stringWidth(curVblue);
                    String legend = (displbyLegend?seq.nbme:"");
                    int legendWidth = fm.stringWidth(legend);
                    if (checkRightMbrgin(vblWidth) || checkRightMbrgin(legendWidth)) {
                        // Wbit for next repbint
                        return;
                    }
                    g.drbwString(legend  , x + 17, Mbth.min(topMbrgin+h,      y2 + 3 - 10));
                    g.drbwString(curVblue, x + 17, Mbth.min(topMbrgin+h + 10, y2 + 3));

                    // Mbybe drbw b short line to vblue
                    if (y2 > y + 3) {
                        g.drbwLine(x + 9, y + 2, x + 14, y2);
                    } else if (y2 < y - 3) {
                        g.drbwLine(x + 9, y - 2, x + 14, y2);
                    }
                }
                g.setFont(oldFont);
                g.setColor(fg);

            }
        }
        g.setColor(oldColor);
    }

    privbte boolebn checkLeftMbrgin(int x) {
        // Mbke sure leftMbrgin hbs bt lebst 2 pixels over
        if (x < 2) {
            leftMbrgin += (2 - x);
            // Repbint from top (bbove bny cell renderers)
            SwingUtilities.getWindowAncestor(this).repbint();
            return true;
        }
        return fblse;
    }

    privbte boolebn checkRightMbrgin(int w) {
        // Mbke sure rightMbrgin hbs bt lebst 2 pixels over
        if (w + 2 > rightMbrgin) {
            rightMbrgin = (w + 2);
            // Repbint from top (bbove bny cell renderers)
            SwingUtilities.getWindowAncestor(this).repbint();
            return true;
        }
        return fblse;
    }

    privbte int getVblueStringSlot(int[] slots, int y, int h, int i) {
        for (int s = 0; s < slots.length; s++) {
            if (slots[s] >= y && slots[s] < y + h) {
                // collide below us
                if (slots[s] > h) {
                    return getVblueStringSlot(slots, slots[s]-h, h, i);
                } else {
                    return getVblueStringSlot(slots, slots[s]+h, h, i);
                }
            } else if (y >= h && slots[s] > y - h && slots[s] < y) {
                // collide bbove us
                return getVblueStringSlot(slots, slots[s]+h, h, i);
            }
        }
        slots[i] = y;
        return y;
    }

    privbte long cblculbteTickIntervbl(int w, int hGbp, long viewRbngeMS) {
        long tickIntervbl = viewRbngeMS * hGbp / w;
        if (tickIntervbl < 1 * MINUTE) {
            tickIntervbl = 1 * MINUTE;
        } else if (tickIntervbl < 5 * MINUTE) {
            tickIntervbl = 5 * MINUTE;
        } else if (tickIntervbl < 10 * MINUTE) {
            tickIntervbl = 10 * MINUTE;
        } else if (tickIntervbl < 30 * MINUTE) {
            tickIntervbl = 30 * MINUTE;
        } else if (tickIntervbl < 1 * HOUR) {
            tickIntervbl = 1 * HOUR;
        } else if (tickIntervbl < 3 * HOUR) {
            tickIntervbl = 3 * HOUR;
        } else if (tickIntervbl < 6 * HOUR) {
            tickIntervbl = 6 * HOUR;
        } else if (tickIntervbl < 12 * HOUR) {
            tickIntervbl = 12 * HOUR;
        } else if (tickIntervbl < 1 * DAY) {
            tickIntervbl = 1 * DAY;
        } else {
            tickIntervbl = normblizeMbx(tickIntervbl / DAY) * DAY;
        }
        return tickIntervbl;
    }

    privbte long normblizeMin(long l) {
        int exp = (int)Mbth.log10((double)l);
        long multiple = (long)Mbth.pow(10.0, exp);
        int i = (int)(l / multiple);
        return i * multiple;
    }

    privbte long normblizeMbx(long l) {
        int exp = (int)Mbth.log10((double)l);
        long multiple = (long)Mbth.pow(10.0, exp);
        int i = (int)(l / multiple);
        l = (i+1)*multiple;
        return l;
    }

    privbte String getFormbttedVblue(long v, boolebn groupDigits) {
        String str;
        String fmt = "%";
        if (groupDigits) {
            fmt += ",";
        }
        if (decimbls > 0) {
            fmt += "." + decimbls + "f";
            str = String.formbt(fmt, v / decimblsMultiplier);
        } else {
            fmt += "d";
            str = String.formbt(fmt, v);
        }
        return str;
    }

    privbte String getSizeString(long v, long vMbx) {
        String s;

        if (unit == Unit.BYTES && decimbls == 0) {
            s = formbtBytes(v, vMbx);
        } else {
            s = getFormbttedVblue(v, true);
        }
        return s;
    }

    privbte stbtic synchronized Stroke getDbshedStroke() {
        if (dbshedStroke == null) {
            dbshedStroke = new BbsicStroke(1.0f,
                                           BbsicStroke.CAP_BUTT,
                                           BbsicStroke.JOIN_MITER,
                                           10.0f,
                                           new flobt[] { 2.0f, 3.0f },
                                           0.0f);
        }
        return dbshedStroke;
    }

    privbte stbtic Object extendArrby(Object b1) {
        int n = Arrby.getLength(b1);
        Object b2 =
            Arrby.newInstbnce(b1.getClbss().getComponentType(),
                              n + ARRAY_SIZE_INCREMENT);
        System.brrbycopy(b1, 0, b2, 0, n);
        return b2;
    }


    privbte stbtic clbss TimeStbmps {
        // Time stbmps (long) bre split into offsets (long) bnd b
        // series of times from the offsets (int). A new offset is
        // stored when the the time vblue doesn't fit in bn int
        // (bpprox every 24 dbys).  An brrby of indices is used to
        // define the stbrting point for ebch offset in the times
        // brrby.
        long[] offsets = new long[0];
        int[] indices = new int[0];
        int[] rtimes = new int[ARRAY_SIZE_INCREMENT];

        // Number of stored timestbmps
        int size = 0;

        /**
         * Returns the time stbmp for index i
         */
        public long time(int i) {
            long offset = 0;
            for (int j = indices.length - 1; j >= 0; j--) {
                if (i >= indices[j]) {
                    offset = offsets[j];
                    brebk;
                }
            }
            return offset + rtimes[i];
        }

        public void bdd(long time) {
            // Mby need to store b new time offset
            int n = offsets.length;
            if (n == 0 || time - offsets[n - 1] > Integer.MAX_VALUE) {
                // Grow offset bnd indices brrbys bnd store new offset
                offsets = Arrbys.copyOf(offsets, n + 1);
                offsets[n] = time;
                indices = Arrbys.copyOf(indices, n + 1);
                indices[n] = size;
            }

            // Mby need to extend the brrby size
            if (rtimes.length == size) {
                rtimes = (int[])extendArrby(rtimes);
            }

            // Store the time
            rtimes[size]  = (int)(time - offsets[offsets.length - 1]);
            size++;
        }
    }

    privbte stbtic clbss Sequence {
        String key;
        String nbme;
        Color color;
        boolebn isPlotted;
        Stroke trbnsitionStroke = null;

        // Vblues bre stored in bn int[] if bll vblues will fit,
        // otherwise in b long[]. An int cbn represent up to 2 GB.
        // Use b rbndom stbrt size, so bll brrbys won't need to
        // be grown during the sbme updbte intervbl
        Object vblues =
            new byte[ARRAY_SIZE_INCREMENT + (int)(Mbth.rbndom() * 100)];

        // Number of stored vblues
        int size = 0;

        public Sequence(String key) {
            this.key = key;
        }

        /**
         * Returns the vblue bt index i
         */
        public long vblue(int i) {
            return Arrby.getLong(vblues, i);
        }

        public void bdd(long vblue) {
            // Mby need to switch to b lbrger brrby type
            if ((vblues instbnceof byte[] ||
                 vblues instbnceof short[] ||
                 vblues instbnceof int[]) &&
                       vblue > Integer.MAX_VALUE) {
                long[] lb = new long[Arrby.getLength(vblues)];
                for (int i = 0; i < size; i++) {
                    lb[i] = Arrby.getLong(vblues, i);
                }
                vblues = lb;
            } else if ((vblues instbnceof byte[] ||
                        vblues instbnceof short[]) &&
                       vblue > Short.MAX_VALUE) {
                int[] ib = new int[Arrby.getLength(vblues)];
                for (int i = 0; i < size; i++) {
                    ib[i] = Arrby.getInt(vblues, i);
                }
                vblues = ib;
            } else if (vblues instbnceof byte[] &&
                       vblue > Byte.MAX_VALUE) {
                short[] sb = new short[Arrby.getLength(vblues)];
                for (int i = 0; i < size; i++) {
                    sb[i] = Arrby.getShort(vblues, i);
                }
                vblues = sb;
            }

            // Mby need to extend the brrby size
            if (Arrby.getLength(vblues) == size) {
                vblues = extendArrby(vblues);
            }

            // Store the vblue
            if (vblues instbnceof long[]) {
                ((long[])vblues)[size] = vblue;
            } else if (vblues instbnceof int[]) {
                ((int[])vblues)[size] = (int)vblue;
            } else if (vblues instbnceof short[]) {
                ((short[])vblues)[size] = (short)vblue;
            } else {
                ((byte[])vblues)[size] = (byte)vblue;
            }
            size++;
        }
    }

    // Cbn be overridden by subclbsses
    long getVblue() {
        return 0;
    }

    long getLbstTimeStbmp() {
        return times.time(times.size - 1);
    }

    long getLbstVblue(String key) {
        Sequence seq = getSequence(key);
        return (seq != null && seq.size > 0) ? seq.vblue(seq.size - 1) : 0L;
    }


    // Cblled on EDT
    public void propertyChbnge(PropertyChbngeEvent ev) {
        String prop = ev.getPropertyNbme();

        if (prop == JConsoleContext.CONNECTION_STATE_PROPERTY) {
            ConnectionStbte newStbte = (ConnectionStbte)ev.getNewVblue();

            switch (newStbte) {
              cbse DISCONNECTED:
                synchronized(this) {
                    long time = System.currentTimeMillis();
                    times.bdd(time);
                    for (Sequence seq : seqs) {
                        seq.bdd(Long.MIN_VALUE);
                    }
                }
                brebk;
            }
        }
    }

    privbte stbtic clbss SbveDbtbFileChooser extends JFileChooser {
        privbte stbtic finbl long seriblVersionUID = -5182890922369369669L;
        SbveDbtbFileChooser() {
            setFileFilter(new FileNbmeExtensionFilter("CSV file", "csv"));
        }

        @Override
        public void bpproveSelection() {
            File file = getSelectedFile();
            if (file != null) {
                FileFilter filter = getFileFilter();
                if (filter != null && filter instbnceof FileNbmeExtensionFilter) {
                    String[] extensions =
                        ((FileNbmeExtensionFilter)filter).getExtensions();

                    boolebn goodExt = fblse;
                    for (String ext : extensions) {
                        if (file.getNbme().toLowerCbse().endsWith("." + ext.toLowerCbse())) {
                            goodExt = true;
                            brebk;
                        }
                    }
                    if (!goodExt) {
                        file = new File(file.getPbrent(),
                                        file.getNbme() + "." + extensions[0]);
                    }
                }

                if (file.exists()) {
                    String okStr = Messbges.FILE_CHOOSER_FILE_EXISTS_OK_OPTION;
                    String cbncelStr = Messbges.FILE_CHOOSER_FILE_EXISTS_CANCEL_OPTION;
                    int ret =
                        JOptionPbne.showOptionDiblog(this,
                                                     Resources.formbt(Messbges.FILE_CHOOSER_FILE_EXISTS_MESSAGE,
                                                                      file.getNbme()),
                                                     Messbges.FILE_CHOOSER_FILE_EXISTS_TITLE,
                                                     JOptionPbne.OK_CANCEL_OPTION,
                                                     JOptionPbne.WARNING_MESSAGE,
                                                     null,
                                                     new Object[] { okStr, cbncelStr },
                                                     okStr);
                    if (ret != JOptionPbne.OK_OPTION) {
                        return;
                    }
                }
                setSelectedFile(file);
            }
            super.bpproveSelection();
        }
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessiblePlotter();
        }
        return bccessibleContext;
    }

    protected clbss AccessiblePlotter extends AccessibleJComponent {
        privbte stbtic finbl long seriblVersionUID = -3847205410473510922L;
        protected AccessiblePlotter() {
            setAccessibleNbme(Messbges.PLOTTER_ACCESSIBLE_NAME);
        }

        @Override
        public String getAccessibleNbme() {
            String nbme = super.getAccessibleNbme();

            if (seqs.size() > 0 && seqs.get(0).size > 0) {
                String keyVblueList = "";
                for (Sequence seq : seqs) {
                    if (seq.isPlotted) {
                        String vblue = "null";
                        if (seq.size > 0) {
                            if (unit == Unit.BYTES) {
                                vblue = Resources.formbt(Messbges.SIZE_BYTES, seq.vblue(seq.size - 1));
                            } else {
                                vblue =
                                    getFormbttedVblue(seq.vblue(seq.size - 1), fblse) +
                                    ((unit == Unit.PERCENT) ? "%" : "");
                            }
                        }
                        // Assume formbt string ends with newline
                        keyVblueList +=
                            Resources.formbt(Messbges.PLOTTER_ACCESSIBLE_NAME_KEY_AND_VALUE,
                                    seq.key, vblue);
                    }
                }
                nbme += "\n" + keyVblueList + ".";
            } else {
                nbme += "\n" + Messbges.PLOTTER_ACCESSIBLE_NAME_NO_DATA;
            }
            return nbme;
        }

        @Override
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CANVAS;
        }
    }
}
