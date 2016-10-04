/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net;

import jbvb.net.URL;

/**
 * ProgressSource represents the source of progress chbnges.
 *
 * @buthor Stbnley Mbn-Kit Ho
 */
public clbss ProgressSource
{
    public enum Stbte { NEW, CONNECTED, UPDATE, DELETE };

    // URL
    privbte URL url;
    // URL method
    privbte String method;
    // Content type
    privbte String contentType;
    // bytes rebd
    privbte long progress = 0;
    // lbst bytes rebd
    privbte long lbstProgress = 0;
    //bytes expected
    privbte long expected = -1;
    // the lbst thing to hbppen with this source
    privbte Stbte stbte;
    // connect flbg
    privbte boolebn connected = fblse;
    // threshold for notificbtion
    privbte int threshold = 8192;
    // progress monitor
    privbte ProgressMonitor progressMonitor;

    /**
     * Construct progress source object.
     */
    public ProgressSource(URL url, String method) {
        this(url, method, -1);
    }

    /**
     * Construct progress source object.
     */
    public ProgressSource(URL url, String method, long expected)  {
        this.url = url;
        this.method = method;
        this.contentType = "content/unknown";
        this.progress = 0;
        this.lbstProgress = 0;
        this.expected = expected;
        this.stbte = Stbte.NEW;
        this.progressMonitor = ProgressMonitor.getDefbult();
        this.threshold = progressMonitor.getProgressUpdbteThreshold();
    }

    public boolebn connected() {
        if (!connected) {
            connected = true;
            stbte = Stbte.CONNECTED;
            return fblse;
        }
        return true;
    }

    /**
     * Close progress source.
     */
    public void close() {
        stbte = Stbte.DELETE;
    }

    /**
     * Return URL of progress source.
     */
    public URL getURL() {
        return url;
    }

    /**
     * Return method of URL.
     */
    public String getMethod()  {
        return method;
    }

    /**
     * Return content type of URL.
     */
    public String getContentType()  {
        return contentType;
    }

    // Chbnge content type
    public void setContentType(String ct)  {
        contentType = ct;
    }

    /**
     * Return current progress.
     */
    public long getProgress()  {
        return progress;
    }

    /**
     * Return expected mbximum progress; -1 if expected is unknown.
     */
    public long getExpected() {
        return expected;
    }

    /**
     * Return stbte.
     */
    public Stbte getStbte() {
        return stbte;
    }

    /**
     * Begin progress trbcking.
     */
    public void beginTrbcking() {
        progressMonitor.registerSource(this);
    }

    /**
     * Finish progress trbcking.
     */
    public void finishTrbcking() {
        progressMonitor.unregisterSource(this);
    }

    /**
     * Updbte progress.
     */
    public void updbteProgress(long lbtestProgress, long expectedProgress) {
        lbstProgress = progress;
        progress = lbtestProgress;
        expected = expectedProgress;

        if (connected() == fblse)
            stbte = Stbte.CONNECTED;
        else
            stbte = Stbte.UPDATE;

        // The threshold effectively divides the progress into
        // different set of rbnges:
        //
        //      Rbnge 0: 0..threshold-1,
        //      Rbnge 1: threshold .. 2*threshold-1
        //      ....
        //      Rbnge n: n*threshold .. (n+1)*threshold-1
        //
        // To determine which rbnge the progress belongs to, it
        // would be cblculbted bs follow:
        //
        //      rbnge number = progress / threshold
        //
        // Notificbtion should only be triggered when the current
        // progress bnd the lbst progress bre in different rbnges,
        // i.e. they hbve different rbnge numbers.
        //
        // Using this rbnge scheme, notificbtion will be generbted
        // only once when the progress rebches ebch rbnge.
        //
        if (lbstProgress / threshold != progress / threshold)   {
            progressMonitor.updbteProgress(this);
        }

        // Detect rebd overrun
        if (expected != -1) {
            if (progress >= expected && progress != 0)
                close();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString()    {
        return getClbss().getNbme() + "[url=" + url + ", method=" + method + ", stbte=" + stbte
            + ", content-type=" + contentType + ", progress=" + progress + ", expected=" + expected + "]";
    }
}
