/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.policytool;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for the policytool.
 *
 */
public clbss Resources_it extends jbvb.util.ListResourceBundle {

    privbte stbtic finbl Object[][] contents = {
        {"NEWLINE", "\n"},
        {"Wbrning.A.public.key.for.blibs.signers.i.does.not.exist.Mbke.sure.b.KeyStore.is.properly.configured.",
                "Avvertenzb: non esiste unb chibve pubblicb per l''blibs {0}. Verificbre che il keystore sib configurbto correttbmente."},
        {"Wbrning.Clbss.not.found.clbss", "Avvertenzb: clbsse non trovbtb: {0}"},
        {"Wbrning.Invblid.brgument.s.for.constructor.brg",
                "Avvertenzb: brgomento o brgomenti non vblidi per il costruttore {0}"},
        {"Illegbl.Principbl.Type.type", "Tipo principbl non vblido: {0}"},
        {"Illegbl.option.option", "Opzione non vblidb: {0}"},
        {"Usbge.policytool.options.", "Uso: policytool [opzioni]"},
        {".file.file.policy.file.locbtion",
                "  [-file <file>]    posizione del file dei criteri"},
        {"New", "Nuovo"},
        {"Open", "Apri"},
        {"Sbve", "Sblvb"},
        {"Sbve.As", "Sblvb con nome"},
        {"View.Wbrning.Log", "Visublizzb registro bvvertenze"},
        {"Exit", "Esci"},
        {"Add.Policy.Entry", "Aggiungi voce dei criteri"},
        {"Edit.Policy.Entry", "Modificb voce dei criteri"},
        {"Remove.Policy.Entry", "Rimuovi voce dei criteri"},
        {"Edit", "Modificb"},
        {"Retbin", "Mbntieni"},

        {"Wbrning.File.nbme.mby.include.escbped.bbckslbsh.chbrbcters.It.is.not.necessbry.to.escbpe.bbckslbsh.chbrbcters.the.tool.escbpes",
            "Avvertenzb: il nome file pu\u00F2 includere bbrre rovescibte con escbpe. Non \u00E8 necessbrio eseguire l'escbpe delle bbrre rovescibte (se necessbrio lo strumento esegue l'escbpe dei cbrbtteri bl momento dellb scritturb del contenuto dei criteri nell'breb di memorizzbzione persistente).\n\nFbre click su Mbntieni per conservbre il nome immesso, oppure su Modificb per modificbre il nome."},

        {"Add.Public.Key.Alibs", "Aggiungi blibs chibve pubblicb"},
        {"Remove.Public.Key.Alibs", "Rimuovi blibs chibve pubblicb"},
        {"File", "File"},
        {"KeyStore", "Keystore"},
        {"Policy.File.", "File dei criteri:"},
        {"Could.not.open.policy.file.policyFile.e.toString.",
                "Impossibile bprire il file di criteri {0}: {1}"},
        {"Policy.Tool", "Strumento criteri"},
        {"Errors.hbve.occurred.while.opening.the.policy.configurbtion.View.the.Wbrning.Log.for.more.informbtion.",
                "Si sono verificbti errori durbnte l'bperturb dellb configurbzione dei criteri. Consultbre il registro delle bvvertenze per ulteriori informbzioni."},
        {"Error", "Errore"},
        {"OK", "OK"},
        {"Stbtus", "Stbto"},
        {"Wbrning", "Avvertenzb"},
        {"Permission.",
                "Autorizzbzione:                                                       "},
        {"Principbl.Type.", "Tipo principbl:"},
        {"Principbl.Nbme.", "Nome principbl:"},
        {"Tbrget.Nbme.",
                "Nome destinbzione:                                                    "},
        {"Actions.",
                "Azioni:                                                             "},
        {"OK.to.overwrite.existing.file.filenbme.",
                "OK per sovrbscrivere il file {0}?"},
        {"Cbncel", "Annullb"},
        {"CodeBbse.", "CodeBbse:"},
        {"SignedBy.", "SignedBy:"},
        {"Add.Principbl", "Aggiungi principbl"},
        {"Edit.Principbl", "Modificb principbl"},
        {"Remove.Principbl", "Rimuovi principbl"},
        {"Principbls.", "Principbl:"},
        {".Add.Permission", "  Aggiungi butorizzbzione"},
        {".Edit.Permission", "  Modificb butorizzbzione"},
        {"Remove.Permission", "Rimuovi butorizzbzione"},
        {"Done", "Fine"},
        {"KeyStore.URL.", "URL keystore:"},
        {"KeyStore.Type.", "Tipo keystore:"},
        {"KeyStore.Provider.", "Provider keystore:"},
        {"KeyStore.Pbssword.URL.", "URL pbssword keystore:"},
        {"Principbls", "Principbl:"},
        {".Edit.Principbl.", "  Modificb principbl:"},
        {".Add.New.Principbl.", "  Aggiungi nuovo principbl:"},
        {"Permissions", "Autorizzbzioni"},
        {".Edit.Permission.", "  Modificb butorizzbzione:"},
        {".Add.New.Permission.", "  Aggiungi nuovb butorizzbzione:"},
        {"Signed.By.", "Firmbto db:"},
        {"Cbnnot.Specify.Principbl.with.b.Wildcbrd.Clbss.without.b.Wildcbrd.Nbme",
            "Impossibile specificbre principbl con unb clbsse cbrbttere jolly senzb un nome cbrbttere jolly"},
        {"Cbnnot.Specify.Principbl.without.b.Nbme",
            "Impossibile specificbre principbl senzb un nome"},
        {"Permission.bnd.Tbrget.Nbme.must.hbve.b.vblue",
                "L'butorizzbzione e il nome destinbzione non possono essere nulli"},
        {"Remove.this.Policy.Entry.", "Rimuovere questb voce dei criteri?"},
        {"Overwrite.File", "Sovrbscrivi file"},
        {"Policy.successfully.written.to.filenbme",
                "I criteri sono stbti scritti in {0}"},
        {"null.filenbme", "nome file nullo"},
        {"Sbve.chbnges.", "Sblvbre le modifiche?"},
        {"Yes", "S\u00EC"},
        {"No", "No"},
        {"Policy.Entry", "Voce dei criteri"},
        {"Sbve.Chbnges", "Sblvb le modifiche"},
        {"No.Policy.Entry.selected", "Nessunb voce dei criteri selezionbtb"},
        {"Unbble.to.open.KeyStore.ex.toString.",
                "Impossibile bprire il keystore: {0}"},
        {"No.principbl.selected", "Nessun principbl selezionbto"},
        {"No.permission.selected", "Nessunb butorizzbzione selezionbtb"},
        {"nbme", "nome"},
        {"configurbtion.type", "tipo di configurbzione"},
        {"environment.vbribble.nbme", "nome vbribbile bmbiente"},
        {"librbry.nbme", "nome librerib"},
        {"pbckbge.nbme", "nome pbckbge"},
        {"policy.type", "tipo di criteri"},
        {"property.nbme", "nome propriet\u00E0"},
        {"provider.nbme", "nome provider"},
        {"url", "url"},
        {"method.list", "listb metodi"},
        {"request.hebders.list", "listb intestbzioni di richiestb"},
        {"Principbl.List", "Listb principbl"},
        {"Permission.List", "Listb butorizzbzioni"},
        {"Code.Bbse", "Codebbse"},
        {"KeyStore.U.R.L.", "URL keystore:"},
        {"KeyStore.Pbssword.U.R.L.", "URL pbssword keystore:"}
    };


    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}
