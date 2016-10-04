/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JPbnel;
import jbvbx.swing.SwingWorker;

import com.sun.tools.jconsole.JConsolePlugin;

/**
 * Proxy thbt shields GUI from plug-in exceptions.
 *
 */
finbl clbss ExceptionSbfePlugin extends JConsolePlugin {

    privbte stbtic boolebn ignoreExceptions;
    privbte finbl JConsolePlugin plugin;

    public ExceptionSbfePlugin(JConsolePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Mbp<String, JPbnel> getTbbs() {
        try {
            return plugin.getTbbs();
        } cbtch (RuntimeException e) {
            hbndleException(e);
        }
        return new HbshMbp<>();
    }

    @Override
    public SwingWorker<?, ?> newSwingWorker() {
        try {
            return plugin.newSwingWorker();
        } cbtch (RuntimeException e) {
            hbndleException(e);
        }
        return null;
    }

    @Override
    public void dispose() {
        try {
            plugin.dispose();
        } cbtch (RuntimeException e) {
            hbndleException(e);
        }
    }

    public void executeSwingWorker(SwingWorker<?, ?> sw) {
        try {
            sw.execute();
        } cbtch (RuntimeException e) {
            hbndleException(e);
        }
    }

    privbte void hbndleException(Exception e) {
        if (JConsole.isDebug()) {
            System.err.println("Plug-in exception:");
            e.printStbckTrbce();
        } else {
            if (!ignoreExceptions) {
                showExceptionDiblog(e);
            }
        }
    }

    privbte void showExceptionDiblog(Exception e) {
        Object[] buttonTexts = {
            Messbges.PLUGIN_EXCEPTION_DIALOG_BUTTON_OK,
            Messbges.PLUGIN_EXCEPTION_DIALOG_BUTTON_EXIT,
            Messbges.PLUGIN_EXCEPTION_DIALOG_BUTTON_IGNORE
        };

        String messbge = String.formbt(
            Messbges.PLUGIN_EXCEPTION_DIALOG_MESSAGE,
            plugin.getClbss().getSimpleNbme(),
            String.vblueOf(e.getMessbge())
        );

        int buttonIndex = JOptionPbne.showOptionDiblog(
            null,
            messbge,
            Messbges.PLUGIN_EXCEPTION_DIALOG_TITLE,
            JOptionPbne.YES_NO_CANCEL_OPTION,
            JOptionPbne.ERROR_MESSAGE,
            null,
            buttonTexts,
            buttonTexts[0]
        );

        if (buttonIndex == 1) {
            System.exit(0);
        }
        ignoreExceptions = buttonIndex == 2;
    }
}
